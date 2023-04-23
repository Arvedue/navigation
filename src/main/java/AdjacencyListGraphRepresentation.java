import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;

import java.util.*;

public class AdjacencyListGraphRepresentation {

    public static HashMap<Station, ArrayList<Station>> constructGraphRepresentation(RoadNetwork roadNetwork) {
        if (roadNetwork == null) return null;

        HashMap<Station, ArrayList<Station>> adjacencyList = new HashMap<>();
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

    public static void displayAdjacencyListGraphRepresentation(HashMap<Station, ArrayList<Station>> adjacencyList) {
        if (adjacencyList == null) return;

        System.out.println("AdjacencyList Representation of a Graph");

        int i = 0;
        for (Map.Entry<Station, ArrayList<Station>> entry : adjacencyList.entrySet()) {
            System.out.print(i++ + "\t" + entry.getKey().getName() + " -> (");
            entry.getValue().forEach(s -> System.out.print(s.getName() + ", "));
            System.out.print(")\n");
        }
    }
}