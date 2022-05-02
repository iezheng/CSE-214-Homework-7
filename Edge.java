
//Jackson Zheng
//SBUID: 113362794
//Recitation: R03
/**
 * Represents a edge.
 * 
 * @author Jackson Zheng
 */
public class Edge {
    private Node A;
    private Node B;
    private int cost;

    /**
     * Creates a default edge.
     */
    public Edge() {

    }

    /**
     * Gets a string representation of the edge.
     * 
     * @return A string with vertex A, vertex B, and cost.
     */
    public String toString() {
        return A.getName() + " " + B.getName() + " " + cost;
    }

    /**
     * Compares the cost of the edge with a given edge.
     * 
     * @return -1 when the edge is less than the given edge.
     * @return 1 when the edge is greater than the given edge.
     * @return 0 when the edge is equal to the given edge.
     */
    public int compareTo(Object otherEdge) {
        if (this.cost < ((Edge) otherEdge).getCost()) {
            return -1;
        } else if (this.cost > ((Edge) otherEdge).getCost()) {
            return 1;
        }
        return 0;
    }

    /**
     * Gets the edge's first vertex.
     * 
     * @return A Node representing the first vertex.
     */
    public Node getA() {
        return this.A;
    }

    /**
     * Gets the edge's second vertex.
     * 
     * @return A Node representing the second vertex.
     */
    public Node getB() {
        return this.B;
    }

    /**
     * Gets the edge's cost.
     * 
     * @return A int representing the cost for the edge.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Sets the edge's Node A.
     * 
     * @param A A Node representing the first vertex.
     */
    public void setA(Node A) {
        this.A = A;
    }

    /**
     * Sets the edge's Node B.
     * 
     * @param B A Node representing the second vertex.
     */
    public void setB(Node B) {
        this.B = B;
    }

    /**
     * Sets the edge's cost.
     * 
     * @param cost A int representing the cost for the edge.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}
