import java.util.*;

class Graph {

    static class Vertex {
        HashMap<String, Integer> nbrs = new HashMap<>();
    }

    private HashMap<String, Vertex> vertices = new HashMap<>();

    // Add a vertex
    public void addVertex(String name) {
        vertices.put(name, new Vertex());
    }

    // Add an edge with weight
    public void addEdge(String v1, String v2, int cost) {
        vertices.get(v1).nbrs.put(v2, cost);
        vertices.get(v2).nbrs.put(v1, cost);
    }

    // Display all stations
    public void displayStations() {
        System.out.println("***** Metro Stations *****");
        int i = 1;
        for (String station : vertices.keySet()) {
            System.out.println(i++ + ". " + station);
        }
        System.out.println("**************************");
    }

    // Display the full map
    public void displayMap() {
        System.out.println("\n\tDelhi Metro Map");
        System.out.println("\t------------------");
        for (String station : vertices.keySet()) {
            System.out.println(station + " => ");
            for (Map.Entry<String, Integer> entry : vertices.get(station).nbrs.entrySet()) {
                System.out.println("\t" + entry.getKey() + "\t" + entry.getValue());
            }
        }
        System.out.println("\t------------------");
    }

    // Dijkstra's algorithm
    public int dijkstra(String src, String dest, boolean calcTime) {
        class Pair {
            String name;
            int cost;

            Pair(String name, int cost) {
                this.name = name;
                this.cost = cost;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        HashMap<String, Integer> dist = new HashMap<>();

        for (String station : vertices.keySet()) {
            dist.put(station, Integer.MAX_VALUE);
        }
        dist.put(src, 0);
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (current.name.equals(dest)) {
                return current.cost;
            }

            for (Map.Entry<String, Integer> nbr : vertices.get(current.name).nbrs.entrySet()) {
                int edgeWeight = calcTime ? 2 * nbr.getValue() : nbr.getValue();
                int newCost = current.cost + edgeWeight;

                if (newCost < dist.get(nbr.getKey())) {
                    dist.put(nbr.getKey(), newCost);
                    pq.add(new Pair(nbr.getKey(), newCost));
                }
            }
        }

        return -1; // Destination unreachable
    }
}

// Main driver
public class MetroMap {

    public static void main(String[] args) {
        Graph metro = new Graph();

        metro.addVertex("Noida Sector 62~B");
        metro.addVertex("Botanical Garden~B");
        metro.addVertex("Yamuna Bank~B");
        metro.addVertex("Rajiv Chowk~BY");
        metro.addVertex("Vaishali~B");
        metro.addVertex("Dwarka~B");

        metro.addEdge("Noida Sector 62~B", "Botanical Garden~B", 8);
        metro.addEdge("Botanical Garden~B", "Yamuna Bank~B", 10);
        metro.addEdge("Yamuna Bank~B", "Rajiv Chowk~BY", 12);
        metro.addEdge("Rajiv Chowk~BY", "Vaishali~B", 7);
        metro.addEdge("Rajiv Chowk~BY", "Dwarka~B", 15);

        metro.displayMap();
        metro.displayStations();

        System.out.println("\nShortest Distance (Noida Sector 62 to Dwarka): " +
                metro.dijkstra("Noida Sector 62~B", "Dwarka~B", false) + " km");

        System.out.println("Shortest Time (Noida Sector 62 to Dwarka): " +
                metro.dijkstra("Noida Sector 62~B", "Dwarka~B", true) + " units");

        System.out.println("\nShortest Distance (Noida Sector 62 to Vaishali): " +
                metro.dijkstra("Noida Sector 62~B", "Vaishali~B", false) + " km");

        System.out.println("Shortest Time (Noida Sector 62 to Vaishali): " +
                metro.dijkstra("Noida Sector 62~B", "Vaishali~B", true) + " units");
    }
}
