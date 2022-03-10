package com.DeliveryAPI.DeliveryAPI.datastructures.ResponseMessages;

import java.util.ArrayList;

/**
 * The path response message that contains an ordered list of string represents the path, from a source to destination, and the total distance.
 */
public class PathResponseMessage extends ResponseMessage {
    private ArrayList<String> path;
    private double distance;

    /**
     * Instantiates a new Path response message.
     *
     * @param path     the path
     * @param distance the distance
     */
    public PathResponseMessage(ArrayList<String> path, double distance) {
        this.path = path;
        this.distance = distance;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public ArrayList<String> getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
