package org.example.task_2;

import java.util.*;

public class MinimumCostPaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Read the number of test cases
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int t = 0; t < testCases; t++) {
            if (t > 0) {
                scanner.nextLine(); // Read empty line separating test cases
            }

            // Number of cities
            int numberOfCities = Integer.parseInt(scanner.nextLine());

            // Map to store city names and their corresponding indices
            Map<String, Integer> cityIndexMap = new HashMap<>();

            // Graph represented as an adjacency list
            List<List<Edge>> graph = new ArrayList<>();

            // Read each city and its neighbors
            for (int i = 0; i < numberOfCities; i++) {
                String cityName = scanner.nextLine(); // City name
                cityIndexMap.put(cityName, i); // Assign a unique index to the city
                graph.add(new ArrayList<>()); // Initialize the adjacency list for this city

                int neighbors = Integer.parseInt(scanner.nextLine()); // Number of neighbors
                for (int j = 0; j < neighbors; j++) {
                    String[] neighborData = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(neighborData[0]) - 1; // Neighbor city index (1-based to 0-based)
                    int cost = Integer.parseInt(neighborData[1]); // Transportation cost
                    graph.get(i).add(new Edge(neighborIndex, cost)); // Add the edge to the graph
                }
            }

            // Number of paths to find
            int numberOfPaths = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numberOfPaths; i++) {
                String[] pathData = scanner.nextLine().split(" ");
                String source = pathData[0]; // Source city name
                String destination = pathData[1]; // Destination city name

                // Find the minimum cost from source to destination using Dijkstra's algorithm
                int minCost = findMinimumCost(graph, cityIndexMap.get(source), cityIndexMap.get(destination));
                System.out.println(minCost); // Print the result
            }
        }
        scanner.close(); // Close the scanner
    }

    /**
     * Finds the minimum transportation cost between two cities using Dijkstra's algorithm.
     *
     * @param graph The graph represented as an adjacency list
     * @param start The index of the source city
     * @param end   The index of the destination city
     * @return The minimum cost to reach the destination, or -1 if no path exists
     */
    private static int findMinimumCost(List<List<Edge>> graph, int start, int end) {
        // Array to store the minimum cost to reach each city
        int[] minCost = new int[graph.size()];
        Arrays.fill(minCost, Integer.MAX_VALUE); // Initialize all costs to infinity
        minCost[start] = 0; // Cost to reach the start city is 0

        // Priority queue to process cities in order of their current minimum cost
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.cost));
        pq.add(new Edge(start, 0)); // Start from the source city

        // Process the queue until it's empty
        while (!pq.isEmpty()) {
            Edge current = pq.poll(); // Get the city with the lowest cost

            // If we reached the destination, return the cost
            if (current.node == end) {
                return current.cost;
            }

            // Traverse all neighbors of the current city
            for (Edge neighbor : graph.get(current.node)) {
                int newCost = current.cost + neighbor.cost; // Calculate the new cost to reach the neighbor
                // If the new cost is smaller, update and add to the queue
                if (newCost < minCost[neighbor.node]) {
                    minCost[neighbor.node] = newCost;
                    pq.add(new Edge(neighbor.node, newCost));
                }
            }
        }
        return -1; // Return -1 if no path exists
    }

    /**
     * Represents an edge in the graph.
     */
    private static class Edge {
        int node; // The index of the city
        int cost; // The cost to reach this city

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}