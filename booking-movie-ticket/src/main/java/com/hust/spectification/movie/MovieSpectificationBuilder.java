package com.hust.spectification.movie;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

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

	@SuppressWarnings("deprecation")
	public Specification<Movie> build() {

		SearchCriteria seachCriteria = new SearchCriteria("movieName", "Like", search);
		SearchCriteria minEvaluateCriteria = new SearchCriteria("movieEvaluate", ">=",
				filterForm.getMinMovieEvaluate());
		SearchCriteria maxEvaluateCriteria = new SearchCriteria("movieEvaluate", "<=",
				filterForm.getMaxMovieEvaluate());

		Specification<Movie> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new MovieSpectification(seachCriteria);
		}

		// min evaluate filter
		if (filterForm.getMinMovieEvaluate() != 0) {
			if (where != null) {
				where = where.and(new MovieSpectification(minEvaluateCriteria));
			} else {
				where = new MovieSpectification(minEvaluateCriteria);
			}
		}

		// max evaluate filter
		if (filterForm.getMaxMovieEvaluate() != 0) {
			if (where != null) {
				where = where.and(new MovieSpectification(maxEvaluateCriteria));
			} else {
				where = new MovieSpectification(maxEvaluateCriteria);
			}
		}

		return where;
	}

}
