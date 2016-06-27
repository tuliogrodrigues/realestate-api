package com.realestate.api.controller;

import com.realestate.api.model.Area;
import com.realestate.api.service.PropertyService;
import com.realestate.api.exceptions.NotFoundException;
import com.realestate.api.model.Property;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trodrigues on 26/06/16.
 */
@RestController
@RequestMapping(value = "properties", produces = "application/json")
@Api(description = "Endpoints to list, get, save, update and delete Property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Property> findAll(@ModelAttribute Area area, Pageable pageable) {
		return propertyService.findAll(area, pageable);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Property findById(@PathVariable String id) {
		return propertyService.findById(id)
				.orElseThrow(new NotFoundException("Cannot find any entity with id =" + id));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public Property save(@Validated @RequestBody Property property) {
		return propertyService.save(property);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Property update(
			@PathVariable String id,
			@Validated @RequestBody Property property) {
		return propertyService.update(id, property);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable String id) {
		propertyService.remove(id);
	}
}
