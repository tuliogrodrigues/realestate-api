package com.realestate.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by trodrigues on 26/06/16.
 */

@Data
@EqualsAndHashCode(exclude={"id", "description", "provinces"})
@Document(collection = "properties")
public class Property implements Serializable {

	private static final long serialVersionUID = -2840499984012690178L;

	@Id
	private String id;

	private String title;

	private String description;

	private Integer price;

	@Min(1)@Max(5)
	private Integer beds;

	@Min(1) @Max(4)
	private Integer baths;

	@Min(20) @Max(240)
	private Integer squareMeters;

	@JsonProperty("x")
	@Min(0) @Max(1400)
	private Integer lat;

	@JsonProperty("y")
	@Min(0) @Max(1000)
	private Integer lng;

	private Collection<String> provinces;

	@Override
	public String toString() {
		return "Property{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", beds=" + beds +
				", baths=" + baths +
				", squareMeters=" + squareMeters +
				", lat=" + lat +
				", lng=" + lng +
				", provinces=" + provinces +
				'}';
	}

}
