package com.realestate.api.repository;

import com.realestate.api.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by trodrigues on 26/06/16.
 */
public interface PropertyRepository extends MongoRepository<Property, String> {

	Optional<Property> findById(String id);

	Collection<Property> findByLatAndLng(Integer lat, Integer lng);

	@Query("{'lat':{'$gte': ?0, '$lte': ?1}, " +
			"'lng':{'$gte': ?3, '$lte': ?2}}")
	Page<Property> findByArea(Integer ax, Integer ay, Integer bx, Integer by, Pageable pageable);
}
