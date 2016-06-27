package com.realestate.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by trodrigues on 26/06/16.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Point {

	private Integer x;
	private Integer y;
}
