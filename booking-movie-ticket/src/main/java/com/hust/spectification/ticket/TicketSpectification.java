package com.hust.spectification.ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hust.entity.Ticket;
import com.hust.spectification.SearchCriteria;

public class TicketSpectification implements Specification<Ticket> {

	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;

	public TicketSpectification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criteria.getKey().equalsIgnoreCase("username")) {
			Join join = root.join("ticketsOfUser", JoinType.LEFT);
			return criteriaBuilder.like(join.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}

		if (criteria.getKey().equalsIgnoreCase("numberPhone")) {
			Join join = root.join("ticketsOfUser", JoinType.LEFT);
			return criteriaBuilder.like(join.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}

		if (criteria.getKey().equalsIgnoreCase("ticketCode")) {
			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}

		return null;
	}

}
