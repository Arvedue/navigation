import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;

import java.util.*;

public class AdjacencyListGraphRepresentation {

    // Methode zur Erstellung einer AdjacencyList-Darstellung des Graphen des angegebenen Verkehrsnetzes
    public static HashMap<Station, ArrayList<Station>> constructGraphRepresentation(RoadNetwork roadNetwork) {
        if (roadNetwork == null) return null;

        // Erstellen einer HashMap, die Stationen und alle Nachbarstationen speichert
        HashMap<Station, ArrayList<Station>> adjacencyList = new HashMap<>();

        // ArrayList, die alle Linien des angegebenen RoadNetwork speichert
        List<RoadLine> roadLines = roadNetwork.getLines();

        for (RoadLine roadLine : roadLines) {
            List<Station> stations = roadLine.getStations();
            for (int i = 0; i < stations.size() - 1; i++) {
                Station from = stations.get(i);
                Station to = stations.get(i + 1);

                /* Wenn die Station nicht in der AdjacencyList enthalten ist, dann
                hinzufügen und eine neue ArrayList für Nachbarstationen erstellen */
                if (!adjacencyList.containsKey(from))
                    adjacencyList.put(from, new ArrayList<>());
                // Wenn die adjacencyList die Station bereits enthält, dann fügen Sie den Nachbar der Station hinzu.
                adjacencyList.get(from).add(to);

                /* Weil unser Graph ungerichtet ist (der Zug kann in beide Richtungen fahren),
                müssen wir den Station FROM zum Station TO als Nachbarn hinzufügen */
                if (!adjacencyList.containsKey(to))
                    adjacencyList.put(to, new ArrayList<>());
                adjacencyList.get(to).add(from);
            }
        }

        return adjacencyList;
    }

    // Methode zur Anzeige der adjacencyList-Darstellung des Graphen
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
