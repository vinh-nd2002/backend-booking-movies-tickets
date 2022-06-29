package com.hust.form.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieFilterForm {

	private Integer minMovieEvaluate;

	private Integer maxMovieEvaluate;
}
