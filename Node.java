
//Jackson Zheng
//SBUID: 113362794
//Recitation: R03
/**
 * Represents a city Node.
 * 
 * @author Jackson Zheng
 */
import java.util.LinkedList;
import java.util.HashSet;

public class Node {
    private String name;
    private HashSet<Edge> edges;
    private boolean visited;
    private LinkedList<String> path;
    private int distance;

    /**
     * Creates a default city Node.
     */
    public Node() {

    }

    public Node(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the city.
     * 
     * @return A string representing the city's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the edges of the city.
     * 
     * @return A Hashset representing the city's edges.
     */
    public HashSet<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Gets the vists of the city.
     * 
     * @return true when the city is already visited.
     * @return false when the city is have not been visited.
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * Gets the shortest path to the city from the starting city in Dijkstra's.
     * 
     * @return a LinkedList representing the shortest path from the starting city in
     *         Dijkstra's to
     *         this city.
     */
    public LinkedList<String> getPath() {
        return this.path;
    }

    /**
     * Gets the shortest path from the city to the starting city in Dijkstra's.
     * 
     * @return a int representing the shortest path from the city to the starting
     *         city in Dijkstra's.
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Sets the city's name.
     * 
     * @param name A string representing the city's name.
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the city's edges.
     * 
     * @param edges A Hashset representing the city's edges.
     * 
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Sets if the city is visited.
     * 
     * @param visited A boolean value representing if the city is visited.
     * 
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Sets the shortest path to the city from the starting city in Dijkstra's.
     * 
     * @param path a LinkedList representing the shortest path from the starting
     *             city in Dijkstra's to this city.
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * Sets the shortest path from the city to the starting city in Dijkstra's.
     * 
     * @param distance a int representing the shortest path from the city to the
     *                 starting city in Dijkstra's.
     * 
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
}