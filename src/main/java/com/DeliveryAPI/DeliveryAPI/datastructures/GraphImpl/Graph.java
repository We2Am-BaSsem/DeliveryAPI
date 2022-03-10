package com.DeliveryAPI.DeliveryAPI.datastructures.GraphImpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * The graph class.
 */
public class Graph {
    private final HashSet<GraphNode> nodes = new HashSet<>();

    /**
     * Add a node to the graph.
     *
     * @param node the node
     */
    public void addNode(GraphNode node) {
        this.nodes.add(node);
    }

    /**
     * Gets node from the graph given the node label.
     *
     * @param destinationName the destination name
     * @return the node
     */
    public GraphNode getNode(String destinationName) {
        for (GraphNode node : this.nodes) {
            if (node.getName().compareTo(destinationName) == 0) {
                return node;
            }
        }
        return null;
    }

    /**
     * Calculate shortest path from source, a specific node, for each node.
     *
     * @param source the source
     */
    public void calculateShortestPathFromSource(GraphNode source) {
        source.setWeight(0.0);

        Set<GraphNode> visitedNodes = new HashSet<>();
        Set<GraphNode> unvisitedNodes = new HashSet<>();

        unvisitedNodes.add(source);

        while (unvisitedNodes.size() != 0) {
            GraphNode currentNode = getLowestDistanceNode(unvisitedNodes);
            unvisitedNodes.remove(currentNode);
            for (Map.Entry<GraphNode, Double> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                GraphNode adjacentNode = adjacencyPair.getKey();
                double edgeWeight = adjacencyPair.getValue();
                if (!visitedNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unvisitedNodes.add(adjacentNode);
                }
            }
            visitedNodes.add(currentNode);
        }
    }


    private GraphNode getLowestDistanceNode(Set<GraphNode> unvisitedNodes) {
        GraphNode lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;
        for (GraphNode node : unvisitedNodes) {
            Double nodeDistance = node.getWeight();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(GraphNode evaluationNode,
                                          Double edgeWeigh, GraphNode sourceNode) {
        Double sourceDistance = sourceNode.getWeight();
        if (sourceDistance + edgeWeigh < evaluationNode.getWeight()) {
            evaluationNode.setWeight(sourceDistance + edgeWeigh);
            LinkedList<GraphNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
