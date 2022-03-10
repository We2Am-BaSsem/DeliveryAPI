package com.DeliveryAPI.DeliveryAPI.DBConfig;


import com.DeliveryAPI.DeliveryAPI.Repositories.CityRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = CityRepo.class)
@Configuration
public class MongoDbConfig {
}