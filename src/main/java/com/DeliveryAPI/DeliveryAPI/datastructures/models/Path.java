package com.DeliveryAPI.DeliveryAPI.datastructures.models;

/**
 * The path class.
 */
public class Path {
    private String destination;
    private String distance;

    /**
     * Instantiates a new Path.
     */
    public Path() {
    }

    /**
     * Instantiates a new Path.
     *
     * @param destination the destination
     * @param distance    the distance
     */
    public Path(String destination, String distance) {
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * Gets destination.
     *
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets destination.
     *
     * @param destination the destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

}
