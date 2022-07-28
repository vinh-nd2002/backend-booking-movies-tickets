package com.hust.spectification.movie;

import org.springframework.data.jpa.domain.Specification;

import com.hust.entity.Movie;
import com.hust.form.filter.MovieFilterForm;
import com.hust.spectification.SearchCriteria;

public class MovieSpectificationBuilder {

	private MovieFilterForm filterForm;

	private String search;

	public MovieSpectificationBuilder(MovieFilterForm filterForm, String search) {
		this.filterForm = filterForm;
		this.search = search;
	}

	public Specification<Movie> build() {

		SearchCriteria seachCriteria = new SearchCriteria("movieName", "Like", search);
		SearchCriteria minEvaluateCriteria = new SearchCriteria("movieEvaluate", ">=",
				filterForm.getMinMovieEvaluate());
		SearchCriteria maxEvaluateCriteria = new SearchCriteria("movieEvaluate", "<=",
				filterForm.getMaxMovieEvaluate());

		Specification<Movie> where = null;

		// search
		if (search != null) {
			where = new MovieSpectification(seachCriteria);
		}

		// min evaluate filter
		if (filterForm != null && filterForm.getMinMovieEvaluate() != null) {
			if (where != null) {
				where = where.and(new MovieSpectification(minEvaluateCriteria));
			} else {
				where = new MovieSpectification(minEvaluateCriteria);
			}
		}

		// max evaluate filter
		if (filterForm != null && filterForm.getMaxMovieEvaluate() != null) {
			if (where != null) {
				where = where.and(new MovieSpectification(maxEvaluateCriteria));
			} else {
				where = new MovieSpectification(maxEvaluateCriteria);
			}
		}

		return where;
	}

}
