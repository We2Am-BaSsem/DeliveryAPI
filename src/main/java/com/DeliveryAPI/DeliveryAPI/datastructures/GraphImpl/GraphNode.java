package com.DeliveryAPI.DeliveryAPI.datastructures.GraphImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The graph node class.
 */
public class GraphNode {
    private String name;
    private double weight = Double.MAX_VALUE;
    private List<GraphNode> shortestPath = new LinkedList<>();
    private final HashMap<GraphNode, Double> adjacentNodes = new HashMap<GraphNode, Double>();

    /**
     * Instantiates a new Graph node.
     *
     * @param name the name
     */
    public GraphNode(String name) {
        this.name = name;
    }

    /**
     * Add adjacent node.
     *
     * @param adjacentNode the adjacent node
     * @param weight       the weight
     */
    public void addAdjacentNode(GraphNode adjacentNode, double weight) {
        this.adjacentNodes.put(adjacentNode, weight);
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
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets shortest path.
     *
     * @return the shortest path
     */
    public List<GraphNode> getShortestPath() {
        return shortestPath;
    }

    /**
     * Sets shortest path.
     *
     * @param shortestPath the shortest path
     */
    public void setShortestPath(List<GraphNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * Gets adjacent nodes.
     *
     * @return the adjacent nodes
     */
    public HashMap<GraphNode, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

}
