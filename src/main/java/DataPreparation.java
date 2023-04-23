import entities.RoadNetwork;
import entities.Station;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPreparation {
    private final RoadNetwork roadNetwork;
    private final HashMap<Station, ArrayList<Station>> adjacencyList;

    public DataPreparation() {
        RoadNetworkBuilder tashkentRoadNetwork = new RoadNetworkBuilder();
        this.roadNetwork = tashkentRoadNetwork.createRoadNetwork("Tashkent");
        this.adjacencyList = AdjacencyListGraphRepresentation.constructGraphRepresentation(this.roadNetwork);
    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public HashMap<Station, ArrayList<Station>> getAdjacencyList() {
        return adjacencyList;
    }
}
