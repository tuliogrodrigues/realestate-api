package com.realestate.api.service;

import com.realestate.api.model.Area;
import com.realestate.api.exceptions.BadRequestException;
import com.realestate.api.exceptions.ConflictException;
import com.realestate.api.exceptions.NotFoundException;
import com.realestate.api.model.Property;
import com.realestate.api.model.Province;
import com.realestate.api.repository.PropertyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by trodrigues on 26/06/16.
 */
@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	public Page<Property> findAll(Area area, Pageable pageable) {

		return area == null || (area.getUpperLeft() == null && area.getBottomRight() == null) ?
				propertyRepository.findAll(pageable):
				findByArea(area, pageable);
	}

	public Optional<Property> findById(String id) {
		return propertyRepository.findById(id);
	}

	public Property save(Property property) {
		if(isAlreadyExists(property)) {
			throw new ConflictException("Property already exits ");
		}

		property.setProvinces(getProvinces(property));
		return propertyRepository.save(property);
	}

	public Property update(String id, Property property) {
		return propertyRepository
				.findById(id)
				.map(p -> {
					property.setProvinces(getProvinces(property));
					BeanUtils.copyProperties(property, p, "id");
					return propertyRepository.save(p);
				}).orElseThrow(new NotFoundException("Cannot find Property with id " + id));
	}

	public void remove(String id) {
		propertyRepository
				.findById(id)
				.ifPresent(propertyRepository::delete);
	}

	private Page<Property> findByArea(Area area, Pageable pageable) {

		if(!isValidArea(area)) {
			throw new BadRequestException("There's no valid area to points specified");
		}

		return propertyRepository.findByArea(
				area.getUpperLeft().getX(),
				area.getUpperLeft().getY(),
				area.getBottomRight().getX(),
				area.getBottomRight().getY(),
				pageable);
	}

	private boolean isAlreadyExists(Property property) {
		return propertyRepository
				.findByLatAndLng(property.getLat(), property.getLng())
				.contains(property);
	}

	private boolean isValidArea(Area area) {
		return Province.listProvinces(area).size() > 0 ;
	}

	private Collection<String> getProvinces(Property property) {
		return Province
				.listProvinces(property.getLat(), property.getLng())
				.stream()
				.map(Province::getName)
				.collect(Collectors.toList());
	}
}