package com.DeliveryAPI.DeliveryAPI;


import com.DeliveryAPI.DeliveryAPI.Controllers.CityController;
import com.DeliveryAPI.DeliveryAPI.Exceptions.DuplicateCityInsertionException;
import com.DeliveryAPI.DeliveryAPI.Exceptions.DuplicatePathInsertionException;
import com.DeliveryAPI.DeliveryAPI.Repositories.CityRepo;
import com.DeliveryAPI.DeliveryAPI.datastructures.GraphImpl.GraphNode;
import com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages.PathResponseMessage;
import com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages.StringErrorMessage;
import com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages.StringResponseMessage;
import com.DeliveryAPI.DeliveryAPI.datastructures.Server;
import com.DeliveryAPI.DeliveryAPI.datastructures.models.City;
import com.DeliveryAPI.DeliveryAPI.datastructures.models.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebInterface {

    private final transient Server server = new Server();
    @Autowired
    private final CityRepo cityRepo;


    public WebInterface(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @GetMapping("/")
    public Server serverStatus() {
        return server;
    }

    @GetMapping("/enableServer")
    public ResponseEntity<Object> enableServer() {
        server.setServerWorking(true);
        return new ResponseEntity<>(new StringResponseMessage("server is enabled"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/disableServer")
    public ResponseEntity<Object> disableServer() {
        server.setServerWorking(false);
        return new ResponseEntity<>(new StringResponseMessage("server is disabled"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/addCity")
    public ResponseEntity<Object> addCity(@ModelAttribute("name") String cityName) throws DuplicateCityInsertionException {
        if (cityName.isEmpty()) {
            return new ResponseEntity<>(new StringErrorMessage("Empty input is not allowed"), HttpStatus.BAD_REQUEST);
        }
        cityName = cityName.toLowerCase();
        try {
            if (server.isServerWorking()) {
                //  initialize a city
                City city = new City();
                city.setName(cityName);

                CityController cityController = new CityController(cityRepo);
                cityController.addCity(city);
                return new ResponseEntity<>(new StringResponseMessage("city is added successfully"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new StringResponseMessage("server is disabled"), HttpStatus.BAD_REQUEST);
        } catch (DuplicateCityInsertionException ex) {
            return new ResponseEntity<>(new StringErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addPath")
    public ResponseEntity<Object> addPath(
            @ModelAttribute("cityname") String cityName,
            @ModelAttribute("destination") String destination,
            @ModelAttribute("distance") String distance) throws DuplicatePathInsertionException {
        cityName = cityName.toLowerCase();
        destination = destination.toLowerCase();
        if (cityName.isEmpty() || destination.isEmpty() || distance.isEmpty()) {
            return new ResponseEntity<>(new StringErrorMessage("Empty input is not allowed"), HttpStatus.BAD_REQUEST);
        }
        try {
            if (server.isServerWorking()) {
                Path path = new Path(destination, distance);
                CityController cityController = new CityController(cityRepo);
                cityController.addPath(cityName, path);
                return new ResponseEntity<>(new StringResponseMessage("path is added successfully"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new StringResponseMessage("server is disabled"), HttpStatus.BAD_REQUEST);
        } catch (DuplicatePathInsertionException ex) {
            return new ResponseEntity<>(new StringErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getshortestpath")
    public ResponseEntity<Object> getShortestPath(
            @ModelAttribute("name") String cityName,
            @ModelAttribute("destination") String destination) throws DuplicatePathInsertionException {
        if (cityName.isEmpty() || destination.isEmpty()) {
            return new ResponseEntity<>(new StringErrorMessage("Empty input is not allowed"), HttpStatus.BAD_REQUEST);
        }
        cityName = cityName.toLowerCase();
        destination = destination.toLowerCase();
        try {
            if (server.isServerWorking()) {
                CityController cityController = new CityController(cityRepo);
                GraphNode path = cityController.findShortestPath(cityName, destination);
                return new ResponseEntity<>(
                        new PathResponseMessage(cityController.prepareOutput(path), path.getWeight()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new StringResponseMessage("server is disabled"), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new StringErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
