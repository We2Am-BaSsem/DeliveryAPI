package com.DeliveryAPI.DeliveryAPI.Repositories;

import com.DeliveryAPI.DeliveryAPI.datastructures.models.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PathRepo extends MongoRepository<Path, Integer> {
    @Query("{'key':?0}")
    Path findByKey(String key);
}
