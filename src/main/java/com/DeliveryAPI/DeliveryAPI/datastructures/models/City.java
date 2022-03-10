package com.DeliveryAPI.DeliveryAPI.datastructures.models;

import java.util.ArrayList;

/**
 * The city class.
 */
public class City {
    private String name;
    private ArrayList<Path> Paths;

    /**
     * Instantiates a new City.
     */
    public City() {
        this.name = "";
        Paths = new ArrayList<>();
    }

    /**
     * Instantiates a new City.
     *
     * @param name  the name
     * @param paths the paths
     */
    public City(String name, ArrayList<Path> paths) {
        this.name = name;
        Paths = paths;
    }

    /**
     * Instantiates a new City.
     *
     * @param name the name
     */
    public City(String name) {
        this.name = name;
        Paths = new ArrayList<>();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets paths.
     *
     * @return the paths
     */
    public ArrayList<Path> getPaths() {
        return Paths;
    }

    /**
     * Sets paths.
     *
     * @param paths the paths
     */
    public void setPaths(ArrayList<Path> paths) {
        Paths = paths;
    }

    /**
     * Append path.
     *
     * @param path the path
     */
    public void appendPath(Path path) {
        this.Paths.add(path);
    }
}
