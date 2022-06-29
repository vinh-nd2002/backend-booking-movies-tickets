package com.hust.spectification.movie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hust.entity.Movie;
import com.hust.spectification.SearchCriteria;

public class MovieSpectification implements Specification<Movie> {

	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;

	public MovieSpectification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		switch (criteria.getOperator()) {

		case "Like":
			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");

		case ">=":
			return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

		case "<=":
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

		default:
			return null;
		}

//		if (criteria.getOperator().equalsIgnoreCase("Like")) {
//			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
//		}
//
//		if (criteria.getOperator().equalsIgnoreCase(">=")) {
//			return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
//		}
//
//		if (criteria.getOperator().equalsIgnoreCase("<=")) {
//			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
//		}
//
//		return null;
	}

}
