
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TashkentRoadNetwork roadNetwork = new TashkentRoadNetwork();
        RoadNetwork tashkent = roadNetwork.createRoadNetwork();
//        tashkent.displayRoadNetworkInfo();

        System.out.println();
        HashMap<Station, ArrayList<Station>> adjacencyList = AdjacencyListGraphRepresentation.constructGraphRepresentation(tashkent);
//        AdjacencyListGraphRepresentation.displayAdjacencyListGraphRepresentation();

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        Station start = tashkent.searchStationByName("Toshkent");
        Station destination = tashkent.searchStationByName("Pushkin");

        System.out.println();
        if (start != null && destination != null) {
            List<Station> path = algorithm.findShortestPath(adjacencyList, start, destination);
            System.out.println("Navigation from start to destination Stations: ");
            path.forEach(s -> System.out.println(s.getName() + " (" + s.getRoadLine().getName() + ")"));
        }
        else System.out.println("NullPointerException");
    }
}
