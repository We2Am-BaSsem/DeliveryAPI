package com.DeliveryAPI.DeliveryAPI.Controllers;

import com.DeliveryAPI.DeliveryAPI.Exceptions.DuplicateCityInsertionException;
import com.DeliveryAPI.DeliveryAPI.Exceptions.DuplicatePathInsertionException;
import com.DeliveryAPI.DeliveryAPI.Repositories.CityRepo;
import com.DeliveryAPI.DeliveryAPI.datastructures.GraphImpl.Graph;
import com.DeliveryAPI.DeliveryAPI.datastructures.GraphImpl.GraphNode;
import com.DeliveryAPI.DeliveryAPI.datastructures.models.City;
import com.DeliveryAPI.DeliveryAPI.datastructures.models.Path;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The controller class responsible for Cities management logic.
 *
 * @author We'am Bassem
 */
@Component
public class CityController {

    private final CityRepo cityRepo;

    /**
     * Instantiates a new City controller.
     *
     * @param cityRepo the city repo
     */
    public CityController(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    /**
     * connect to DB and add a city.
     *
     * @param city the city
     * @throws DuplicateCityInsertionException the duplicate city insertion exception
     */
    public void addCity(City city) throws DuplicateCityInsertionException {
        try {
            cityRepo.save(city);
        } catch (Exception ex) {
            throw new DuplicateCityInsertionException(city.getName());
        }
    }

    /**
     * connect to DB add a path to a specific city.
     *
     * @param cityName the city name
     * @param path     the path
     * @throws DuplicatePathInsertionException the duplicate path insertion exception
     */
    public void addPath(String cityName, Path path) throws DuplicatePathInsertionException {
        City city = cityRepo.findByName(cityName);
        for (Path checkPath : city.getPaths()) {
            if (checkPath.getDestination() != null && checkPath.getDestination().compareTo(path.getDestination()) == 0) {
                throw new DuplicatePathInsertionException(path.getDestination());
            }
        }
        cityRepo.deleteByName(city.getName());
        city.appendPath(path);
        cityRepo.save(city);
    }

    /**
     * Find shortest path graph node.
     *
     * @param cityName    the city name
     * @param destination the destination
     * @return the graph node
     */
    public GraphNode findShortestPath(String cityName, String destination) {
        City sourceCity = cityRepo.findByName(cityName);
        GraphNode sourceNode = new GraphNode(sourceCity.getName());
        Collection<GraphNode> nodes = generateGraph(sourceCity, sourceNode).values();
        Graph map = new Graph();
        for (GraphNode node : nodes) {
            map.addNode(node);
        }
        map.calculateShortestPathFromSource(sourceNode);
        return map.getNode(destination);
    }

    private HashMap<String, GraphNode> generateGraph(City sourceCity, GraphNode sourceNode) {
        Queue<City> citiesToBeAdded = new LinkedList<>();
        HashMap<String, GraphNode> citiesNodes = new HashMap<>();
        citiesToBeAdded.add(sourceCity);
        citiesNodes.put(sourceNode.getName(), sourceNode);

        while (!citiesToBeAdded.isEmpty()) {
            City parsedCity = citiesToBeAdded.poll();
            if (citiesNodes.containsKey(parsedCity.getName())) {
                for (Path path : parsedCity.getPaths()) {
                    City newCityToBeAdded = cityRepo.findByName(path.getDestination());
                    if (citiesNodes.containsKey(path.getDestination())){
                        citiesNodes.get(parsedCity.getName()).addAdjacentNode(
                                citiesNodes.get(path.getDestination()),
                                Double.parseDouble(path.getDistance()));
                    } else {
                        GraphNode newNode = new GraphNode(path.getDestination());
                        citiesNodes.get(parsedCity.getName()).addAdjacentNode(
                                newNode,
                                Double.parseDouble(path.getDistance()));
                        citiesNodes.put(newNode.getName(), newNode);
                        if (newCityToBeAdded != null) {
                            citiesToBeAdded.add(newCityToBeAdded);
                        }
                    }
                }
            }
        }

        return citiesNodes;
    }

    /**
     * Prepare output array list.
     *
     * @param node the node
     * @return the array list
     */
    public ArrayList<String> prepareOutput(GraphNode node) {
        ArrayList<String> path = new ArrayList<>();
        for (GraphNode pathNode : node.getShortestPath()) {
            path.add(pathNode.getName());
        }
        path.add(node.getName());
        return path;
    }
}
