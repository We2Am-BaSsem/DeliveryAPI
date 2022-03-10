package com.DeliveryAPI.DeliveryAPI.Services;

import com.DeliveryAPI.DeliveryAPI.Repositories.CityRepo;
import com.DeliveryAPI.DeliveryAPI.Repositories.PathRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DbService {
    @Autowired
    private final CityRepo cityRepo;
    @Autowired
    private final PathRepo pathRepo;


    public DbService(CityRepo cityRepo, PathRepo pathRepo) {
        this.cityRepo = cityRepo;
        this.pathRepo = pathRepo;
    }
}
