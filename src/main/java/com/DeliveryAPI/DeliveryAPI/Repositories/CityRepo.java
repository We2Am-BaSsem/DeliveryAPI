package com.DeliveryAPI.DeliveryAPI.Repositories;

import com.DeliveryAPI.DeliveryAPI.datastructures.models.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CityRepo extends MongoRepository<City, Integer> {
    @Query("{'key':?0}")
    City findByKey(String key);

    @Query("{'name':?0}")
    City findByName(String name);

    @Query(value = "{'name':?0}", delete = true)
    City deleteByName(String name);
}
