package org.example;

import java.util.*;

/**
 * Get minimum frontier edge is initialized that tells the system to get the minimum weight to get from the frontier
 */
public class Main {

    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);

        graph.display();

        System.out.println("\nShortest Paths:");
        shortestPath(graph, 0);

        System.out.println("\nMinimum Spanning Tree:");
        MyGraph mst = minimumSpanningTree(graph, 0);
        mst.display();
    }

    /** Starts the value of the edge to null */
    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited) {
        Edge minEdge = null;
        int minWeight = Integer.MAX_VALUE;

        for (int v : g.vertices) {
            if (visited[v]) {
                for (Edge edge : g.adjacencyList.get(v)) {
                    int neighbour = (edge.v1 == v) ? edge.v2 : edge.v1;
                    if (!visited[neighbour] && edge.weight < minWeight) {
                        minWeight = edge.weight;
                        minEdge = edge;
                    }
                }
            }
        }
        return minEdge;
    }

    /**
     * Would return a new mygraph with this statement
     */
    public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex) {
        MyGraph mst = new MyGraph();
        for (int v : g.vertices) mst.addVertex(v);
        boolean[] visited = new boolean[g.vertices.size()];
        visited[startingVertex] = true;

        for (int i = 1; i < g.vertices.size(); i++) {
            Edge minEdge = getMinFrontierEdge(g, visited);
            if (minEdge != null) { // Ensure minEdge is not null before using it
                mst.addEdge(minEdge.v1, minEdge.v2, minEdge.weight);
                visited[minEdge.v1] = true;
                visited[minEdge.v2] = true;
            }
        }

        return mst;
    }

    /** Shortest paths using the Dikstra algorithm */
    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist) {
        int minDist = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int v : unvisitedList) {
            if (dist[v] < minDist) {
                minDist = dist[v];
                minVertex = v;
            }
        }
        return minVertex;
    }

    public static void shortestPath(MyGraph g, int startingVertex) {
        int[] dist = new int[g.vertices.size()];
        int[] prev = new int[g.vertices.size()];
        List<Integer> unvisited = new ArrayList<>(g.vertices);

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[startingVertex] = 0;

        while (!unvisited.isEmpty()) {
            int u = getMinDistVertex(g, unvisited, dist);
            if (u == -1) break; // Avoid infinite loop if no unvisited vertices have finite distance
            unvisited.remove((Integer) u);

            for (Edge edge : g.adjacencyList.get(u)) {
                int v = (edge.v1 == u) ? edge.v2 : edge.v1;
                if (unvisited.contains(v)) {
                    int alt = dist[u] + edge.weight;
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                    }
                }
            }
        }

        System.out.println("Distances: " + Arrays.toString(dist));
        System.out.println("Previous: " + Arrays.toString(prev));
    }
}
