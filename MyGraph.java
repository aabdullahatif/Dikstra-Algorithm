import java.util.*;

public class MyGraph {
    /** Calls on all the vertices that are the part of the class */
    private List<Integer> vertices;
    private Map<Integer, List<Edge>> adjacencyList;

    /** Construcotr is being created which is made the default */
    public MyGraph() {
        vertices = new ArrayList<>();
        adjacencyList = new HashMap<>();
    }

    /** A new vertex is added to the already exsited ones */
    public void addVertex(int v) {
        vertices.add(v);
        adjacencyList.put(v, new ArrayList<>());
    }

    /** A new edge is created to add */
    public void addEdge(int v1, int v2, int weight) {
        Edge edge = new Edge(v1, v2, weight);
        adjacencyList.get(v1).add(edge);
        adjacencyList.get(v2).add(edge);
    }

    /** The graph will be desplayed which would show all the values in the data */
    public void display() {
        System.out.println("Vertices: " + vertices);
        for (int v : adjacencyList.keySet()) {
            System.out.print(v + ": ");
            for (Edge edge : adjacencyList.get(v)) {
                System.out.print("(" + edge.v1 + ", " + edge.v2 + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}

