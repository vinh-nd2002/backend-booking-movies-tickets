package com.hust.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hust.entity.Image;
import com.hust.entity.Movie;
import com.hust.form.filter.MovieFilterForm;
import com.hust.repository.IMovieRepository;
import com.hust.spectification.movie.MovieSpectificationBuilder;

@Service
@Transactional
public class MovieService implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Autowired
	private IImageService imageService;

	@Autowired
	private IGoogleDriveFile googleDriveFile;

	@Override
	public List<Movie> getAllMovies(String search, MovieFilterForm filterForm) {
		MovieSpectificationBuilder builder = new MovieSpectificationBuilder(filterForm, search);
		return movieRepository.findAll(builder.build());
	}

	@Override
	public Movie getMovieById(int id) {
		return movieRepository.getById(id);
	}

	@Override
	public void createMovie(MultipartFile imageForm, String movieName, String movieDescription, String movieTrailer,
			Date movieRelease, short movieLength, short movieEvaluate, int moviePrice, boolean movieStatus) {

		String imgName = StringUtils.cleanPath(imageForm.getOriginalFilename());
		try {
			if (imgName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + imgName);
			}
			googleDriveFile.uploadFile(imageForm);

			Image image = imageService.convertFileToImage(imgName);
			if (image != null) {
				image.setImgType(imageForm.getContentType());

				Movie movie = new Movie();
				movie.setMovieDescription(movieDescription);
				movie.setMovieEvaluate(movieEvaluate);
				movie.setMovieLength(movieLength);
				movie.setMovieName(movieName);
				movie.setMoviePrice(moviePrice);
				movie.setMovieRelease(movieRelease);
				movie.setMovieStatus(movieStatus);
				movie.setMovieTrailer(movieTrailer);

				image.setImgOfMovie(movie);

				movie.setMoviePoster(image);

				movieRepository.save(movie);
//				imageRepository.save(image);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMovieById(int id) throws Exception {
		Movie movie = movieRepository.getById(id);
		googleDriveFile.deleteFile(movie.getMoviePoster().getDriveFileId());
		movieRepository.delete(movie);
	}

	@Override
	public void updateMovie(MultipartFile imageForm, int movieId, String movieName, String movieDescription,
			String movieTrailer, Date movieRelease, short movieLength, short movieEvaluate, int moviePrice,
			boolean movieStatus) {
		// Lấy ra movie hiện tại
		Movie movie = movieRepository.getById(movieId);

		// Update movie
		movie.setMovieDescription(movieDescription);
		movie.setMovieEvaluate(movieEvaluate);
		movie.setMovieLength(movieLength);
		movie.setMovieName(movieName);
		movie.setMoviePrice(moviePrice);
		movie.setMovieRelease(movieRelease);
		movie.setMovieStatus(movieStatus);
		movie.setMovieTrailer(movieTrailer);

		// nếu form update có moviePoster mới
		if (imageForm != null) {

			String imgName = StringUtils.cleanPath(imageForm.getOriginalFilename());

			try {
				if (imgName.contains("..")) {
					throw new Exception("Filename contains invalid path sequence " + imgName);
				}

				if (!imgName.equals(movie.getMoviePoster().getImgName())) {
					// Delete drive image cũ
					googleDriveFile.deleteFile(movie.getMoviePoster().getDriveFileId());

					// Upload drive image mới
					googleDriveFile.uploadFile(imageForm);

					// Xóa database image cũ
					imageService.deleteImage(movie.getMoviePoster().getImgId());

					// Tạo database image mới
					Image newImage = imageService.convertFileToImage(imgName);

					if (newImage != null) {
						newImage.setImgType(imageForm.getContentType());

						newImage.setImgOfMovie(movie);

						movie.setMoviePoster(newImage);

						// save new movie
						movieRepository.save(movie);
//					imageRepository.save(image);
					}

				} else {
					movieRepository.save(movie);
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			movieRepository.save(movie);
		}

	}

}
