package com.realestate.api;

import com.realestate.api.model.Property;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Arrays;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApiApplication.class)
@WebAppConfiguration
public abstract class RealEstateApiApplicationTests {

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	protected String toJson(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	protected Object toEntity(String o, Class clazz) throws IOException {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(o.getBytes());
		return mappingJackson2HttpMessageConverter.read(clazz, inputMessage);
	}

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
				hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
	}

	protected Property createProperty(String title, String description, Integer price,
	                                  Integer lat, Integer lng, Integer beds, Integer baths,
	                                  Integer squareMeters) {

		Property property = new Property();

		property.setTitle(title);
		property.setDescription(description);
		property.setPrice(price);
		property.setLat(lat);
		property.setLng(lng);
		property.setBeds(beds);
		property.setBaths(baths);
		property.setSquareMeters(squareMeters);

		return property;
	}
}
