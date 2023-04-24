import entities.RoadNetwork;
import entities.Station;

import java.util.ArrayList;
import java.util.HashMap;

/* Klasse zur Erstellung von Tashkent Verkehrsnetz */
public class DataPreparation {
    private final RoadNetwork roadNetwork;

    // HashMap, die Stationen und alle Nachbarstationen speichert (AdjacencyList representation of the Graph)
    private final HashMap<Station, ArrayList<Station>> adjacencyList;

    public DataPreparation() {
        RoadNetworkBuilder roadNetworkBuilder = new RoadNetworkBuilder();

        // Erstellung des Verkehrsnetzes von Taschkent
        this.roadNetwork = roadNetworkBuilder.createRoadNetwork("Tashkent");

        // Erstellung der AdjacencyList-Darstellung des Graphen unseres Verkehrsnetzes
        this.adjacencyList = AdjacencyListGraphRepresentation.constructGraphRepresentation(this.roadNetwork);
    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public HashMap<Station, ArrayList<Station>> getAdjacencyList() {
        return adjacencyList;
    }
}
