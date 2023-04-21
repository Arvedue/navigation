import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraphRepresentation {

    private static HashMap<Station, ArrayList<Station>> adjacencyList;

    public static HashMap<Station, ArrayList<Station>> constructGraphRepresentation(RoadNetwork roadNetwork) {
        adjacencyList = new HashMap<>();
        List<RoadLine> roadLines = roadNetwork.getLines();
        for (RoadLine roadLine : roadLines) {
            List<Station> stations = roadLine.getStations();
            for (int i = 0; i < stations.size() - 1; i++) {
                Station from = stations.get(i);
                Station to = stations.get(i + 1);

                if (!adjacencyList.containsKey(from))
                    adjacencyList.put(from, new ArrayList<>());
                adjacencyList.get(from).add(to);

                if (!adjacencyList.containsKey(to))
                    adjacencyList.put(to, new ArrayList<>());
                adjacencyList.get(to).add(from);
            }
        }

        return adjacencyList;
    }

    public static void displayAdjacencyListGraphRepresentation() {
        System.out.println("AdjacencyList Representation of a Graph");
        int i = 0;
        for (Map.Entry<Station, ArrayList<Station>> entry : adjacencyList.entrySet()) {
            System.out.print(i++ + "\t" + entry.getKey().getName() + " -> (");
            entry.getValue().forEach(s -> System.out.print(s.getName() + ", "));
            System.out.print(")\n");
        }
    }
}
