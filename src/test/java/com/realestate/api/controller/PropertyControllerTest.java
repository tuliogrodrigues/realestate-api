package com.realestate.api.controller;

import com.realestate.api.RealEstateApiApplicationTests;
import com.realestate.api.model.Property;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by trodrigues on 27/06/16.
 */
public class PropertyControllerTest extends RealEstateApiApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturnProperties() throws Exception {

		mockMvc.perform(get("/properties")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("totalElements").value(Matchers.greaterThan(0)));
	}

	@Test
	public void shouldNotReturnProperties() throws Exception {

		mockMvc.perform(get("/properties?ax=10&ay=10&bx=20&by=20")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("totalElements").value(0));
	}

	@Test
	public void shouldReturnNotFound() throws Exception {

		mockMvc.perform(get("/properties/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturnSavedProperty() throws Exception {

		Property property = createProperty(
				"Imóvel código 665, com 1 quarto e 1 banheiro",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				540000,667,556,1,1,42
		);

		mockMvc.perform(post("/properties")
				.content(toJson(property))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists());

	}

	@Test
	public void shouldReturnConflict() throws Exception {

		Property property = createProperty(
				"Imóvel código 665, com 1 quarto e 1 banheiro",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				540000,667,556,1,1,42
		);

		mockMvc.perform(post("/properties")
				.content(toJson(property))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}

	@Test
	public void shouldUpdateProperty() throws Exception {

		Property property = createProperty(
				"Imóvel código 665, com 2 quartos e 1 banheiro",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				600000,660,450,2,1,50
		);

		MvcResult result = mockMvc.perform(post("/properties")
				.content(toJson(property))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("beds").value(2))
				.andReturn();


		Property savedProperty = (Property) toEntity(result.getResponse().getContentAsString(), Property.class);
		savedProperty.setBeds(3);

		mockMvc.perform(put("/properties/{id}", savedProperty.getId())
				.content(toJson(savedProperty))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").value(savedProperty.getId()))
				.andExpect(jsonPath("beds").value(3))
				.andReturn();
	}
}
