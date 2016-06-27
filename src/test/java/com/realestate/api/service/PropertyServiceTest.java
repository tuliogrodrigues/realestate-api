package com.realestate.api.service;

import com.realestate.api.RealEstateApiApplicationTests;
import com.realestate.api.model.Area;
import com.realestate.api.model.Province;
import com.realestate.api.model.Property;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by trodrigues on 26/06/16.
 */
public class PropertyServiceTest extends RealEstateApiApplicationTests {

	@Autowired
	private PropertyService service;

	@Before
	public void addProperties() {

		service.save(createProperty(
				"Imóvel código 665, com 1 quarto e 1 banheiro",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				540000,667,556,1,1,42
		));

		service.save(createProperty(
				"Imóvel código 34, com 4 quartos e 3 banheiros",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
				1250000,999,333,4,3,237
		));
	}

	@After
	public void removeProperties() {
		service.findAll(null, new PageRequest(0, 5))
				.forEach(c -> service.remove(c.getId()));
	}

	@Test
	public void shouldReturnAllProperties() {

		Page result = service.findAll(null, new PageRequest(0,10));

		Assert.notNull(result);
		Assert.isTrue(result.getTotalElements() > 0);
	}

	@Test
	public void shouldReturnProperties() {

		Area area = new Area(10,1000,600,30);
		Page result = service.findAll(area, new PageRequest(0,10));

		Assert.notNull(result);
		Assert.isTrue(result.getTotalElements() > 0);
	}

	@Test
	public void shouldReturnRujaProperties() {

		Area area = new Area(400,1000,1100,500);
		Page result = service.findAll(area, new PageRequest(0,10));

		Assert.notNull(result);
		Assert.isTrue(result.getTotalElements() == 1);

		((List<Property>)result.getContent())
				.forEach(p -> Assert.isTrue(p.getProvinces().contains(Province.RUJA.getName())));
	}
}
