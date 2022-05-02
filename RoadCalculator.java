
//Jackson Zheng
//SBUID: 113362794
//Recitation: R03
/**
 * Represents a road calculator.
 * 
 * @author Jackson Zheng
 */
import java.security.KeyStore.Entry;
import java.util.*;
import big.data.DataSource;

public class RoadCalculator {
    private static HashMap<String, Node> graph = new HashMap<String, Node>();
    private static LinkedList<Edge> mst;
    private static LinkedList<Node> djikstra = new LinkedList<Node>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter graph URL");
        String url = input.nextLine();
        System.out.println("Loading Map...");
        graph = buildGraph(url);
        System.out.println();
        System.out.println("Cities:");
        System.out.println();
        for (Node city : graph.values()) {
            System.out.println(city.getName());
        }
        System.out.println();
        System.out.println("Roads:");
        System.out.println();
        ArrayList<String> printRoad = new ArrayList<String>();
        for (Node city : graph.values()) {
            for (Edge edge : city.getEdges()) {
                boolean inPrint = false;
                for (int i = 0; i < printRoad.size(); i++) {
                    if ((printRoad.get(i).contains(edge.getA().getName()))
                            && (printRoad.get(i).contains(edge.getB().getName()))) {
                        inPrint = true;
                    }
                }
                if (!inPrint) {
                    printRoad.add(edge.toString());
                }
            }
        }
        for (int i = 0; i < printRoad.size(); i++) {
            System.out.println(printRoad.get(i));
        }
        System.out.println();
        System.out.println("Minimum Spanning Tree:");
        System.out.println();
        mst = buildMST(graph);
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.get(i));
        }
        System.out.println();
        System.out.println("Enter a starting point for shortest path or Q to quit:");
        String startingPoint = input.nextLine();
        while (!startingPoint.equalsIgnoreCase("Q")) {
            System.out.println("Enter a destination: ");
            String destination = input.nextLine();
            int distance = Djikstra(graph, startingPoint, destination);
            System.out.println(distance);
            System.out.println("Path:");
            for (int i = 0; i < djikstra.size(); i++) {
                System.out.print(djikstra.get(i).getName() + ", ");
            }
            System.out.println();
            System.out.println("Enter a starting point for shortest path or Q to quit:");
            startingPoint = input.nextLine();
        }
        System.out.println();
        System.out.println("Goodbye; PSA, there's a cop on the right in 3 miles!");
    }

    public static HashMap<String, Node> buildGraph(String location) {
        DataSource ds = DataSource.connectXML(location);
        ds.load();
        String cityNamesStr = ds.fetchString("cities");
        String[] cityNames = cityNamesStr.substring(1, cityNamesStr.length() -
                1).replace("\"", "").split(",");
        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(1, roadNamesStr.length() -
                1).split("\",\"");
        roadNames[0] = roadNames[0].substring(1, roadNames[0].length());
        roadNames[roadNames.length - 1] = roadNames[roadNames.length - 1].substring(0,
                roadNames[roadNames.length - 1].length() - 1);

        ArrayList<Node> cityNodes = new ArrayList<Node>();
        for (int i = 0; i < cityNames.length; i++) {
            Node newNode = new Node();
            newNode.setName(cityNames[i]);
            cityNodes.add(newNode);
        }

        for (int i = 0; i < cityNodes.size(); i++) {
            HashSet<Edge> edges = new HashSet<Edge>();
            for (int j = 0; j < roadNames.length; j++) {
                if (roadNames[j].split(",")[0].equals(cityNodes.get(i).getName())) {
                    Edge newEdge = new Edge();
                    String[] split = roadNames[j].split(",");
                    newEdge.setA(cityNodes.get(i));
                    for (int k = 0; k < cityNodes.size(); k++) {
                        if (split[1].equalsIgnoreCase(cityNodes.get(k).getName())) {
                            newEdge.setB(cityNodes.get(k));
                        }
                    }
                    newEdge.setCost(Integer.parseInt(split[2]));
                    edges.add(newEdge);
                } else if (roadNames[j].split(",")[1].equals(cityNodes.get(i).getName())) {
                    Edge newEdge = new Edge();
                    String[] split = roadNames[j].split(",");
                    newEdge.setA(cityNodes.get(i));
                    for (int k = 0; k < cityNodes.size(); k++) {
                        if (split[0].equalsIgnoreCase(cityNodes.get(k).getName())) {
                            newEdge.setB(cityNodes.get(k));
                        }
                    }
                    newEdge.setCost(Integer.parseInt(split[2]));
                    edges.add(newEdge);
                }
            }
            cityNodes.get(i).setEdges(edges);
            graph.put(cityNodes.get(i).getName(), cityNodes.get(i));
        }
        return graph;
    }

    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph) {
        ArrayList<Node> notVisited = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        LinkedList<Edge> edges = new LinkedList<Edge>();

        for (Node node : graph.values()) {
            notVisited.add(node);
        }

        Node currentNode = notVisited.remove(0);
        visited.add(currentNode);
        currentNode.setVisited(true);
        while (notVisited.size() != 0) {
            int min = Integer.MAX_VALUE;
            Node tempNode = new Node();
            Edge tempEdge = new Edge();
            for (Node node : visited) {
                for (Edge edge : node.getEdges()) {
                    if ((edge.getB().getVisited() == false) && (edge.getCost() < min)) {
                        min = edge.getCost();
                        tempEdge = edge;
                        tempNode = edge.getB();
                    }
                }
            }
            edges.add(tempEdge);
            int index = notVisited.indexOf(tempNode);
            currentNode = notVisited.remove(index);
            visited.add(currentNode);
            currentNode.setVisited(true);
        }
        return edges;

    }

    public static int Djikstra(HashMap<String, Node> graph, String source, String dest) {
        ArrayList<Node> unsettledNodes = new ArrayList<Node>();
        ArrayList<Node> settledNodes = new ArrayList<Node>();

        for (Node node : graph.values()) {
            node.setVisited(false);
            node.setDistance(Integer.MAX_VALUE);
            unsettledNodes.add(node);
        }
        Node currentNode = unsettledNodes.get(0);
        currentNode.setDistance(0);
        Node nextNode = new Node();

        while (unsettledNodes.size() != 0) {
            for (Edge edge : currentNode.getEdges()) {
                if (currentNode.getDistance() + edge.getCost() < edge.getB().getDistance()) {
                    edge.getB().setDistance(currentNode.getDistance() + edge.getCost());
                    djikstra.add(edge.getB());

                }
            }
            currentNode.setVisited(true);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < unsettledNodes.size(); i++) {
                if ((unsettledNodes.get(i).getDistance() < min) && (unsettledNodes.get(i).getVisited() == false)) {
                    min = unsettledNodes.get(i).getDistance();
                    nextNode = unsettledNodes.get(i);
                }
            }
            if (graph.get(dest).getVisited()) {
                break;
            }
            if (min == Integer.MAX_VALUE) {
                break;
            }
            int index = unsettledNodes.indexOf(currentNode);
            settledNodes.add(unsettledNodes.remove(index));
            currentNode = nextNode;
        }

        return graph.get(dest).getDistance();
    }

}
