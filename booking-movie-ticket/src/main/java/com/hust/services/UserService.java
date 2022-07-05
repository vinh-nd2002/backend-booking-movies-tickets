package com.hust.services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hust.entity.RegistrationUserToken;
import com.hust.entity.User;
import com.hust.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.hust.form.create.UserForm;
import com.hust.repository.IRegistrationUserTokenRepository;
import com.hust.repository.IResetPasswordTokenRepository;
import com.hust.repository.IUserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Thông báo event
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private IRegistrationUserTokenRepository registrationUserTokenRepository;

	@Autowired
	private IResetPasswordTokenRepository resetPasswordTokenRepository;

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public User getUserById(int id) {
		return repository.getById(id);
	}

	@Override
	public User findAccountByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}

	@Override
	public boolean isUserExistsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public boolean isUserExistsByUsername(String username) {
		return repository.existsByUsername(username);
	}

	@Override
	public void createUser(UserForm form) {

		// convert form to user
		TypeMap<UserForm, User> typeMap = modelMapper.getTypeMap(UserForm.class, User.class);
		if (typeMap == null) {
			modelMapper.addMappings(new PropertyMap<UserForm, User>() {
				@Override
				protected void configure() {
					skip(destination.getUserId());
				}
			});
		}

		User user = modelMapper.map(form, User.class);

		// encode password
		user.setPassword((passwordEncoder.encode(user.getPassword())));

		// create user
		repository.save(user);

		// create new user registration token
		createNewRegistrationUserToken(user);
		// send email confirm

		// send email to confirm
		sendConfirmUserRegistrationViaEmail(user.getEmail());
	}

	private void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	private void createNewRegistrationUserToken(User user) {
		// create new token for confirm Registration

		final String newToken = UUID.randomUUID().toString(); // token

		// create new user register token
		RegistrationUserToken userRegisterToken = new RegistrationUserToken(newToken, user);

		registrationUserTokenRepository.save(userRegisterToken);

	}

	@Override
	public User findAccountByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public void activeUser(String token) {
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);
		User user = registrationUserToken.getUser();

		// active user
		user.setActive(true);
		repository.save(user);
		
		// remove user_token_confirm after active account success
		registrationUserTokenRepository.delete(registrationUserToken);
	}

}
