package com.realestate.api;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by trodrigues on 27/06/16.
 */
@Profile("test")
@Configuration
public class MongoTestConfig {

	private static final Fongo MONGO_SERVER = new Fongo("test");

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Bean
	public MongoDbFactory mongoDbFactory() {
		return new MongoDbFactory() {
			@Override
			public DB getDb() throws DataAccessException {
				return MONGO_SERVER.getDB(database);
			}

			@Override
			public DB getDb(String s) throws DataAccessException {
				return MONGO_SERVER.getDB(s);
			}

			@Override
			public PersistenceExceptionTranslator getExceptionTranslator() {
				return new MongoExceptionTranslator();
			}
		};
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongoTemplate;
	}
}
