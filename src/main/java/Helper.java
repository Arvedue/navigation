import entities.RoadNetwork;
import entities.Station;
import utils.AdjacencyListGraphRepresentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Helper {

    private final RoadNetwork roadNetwork;
    private final HashMap<Station, ArrayList<Station>> adjacencyList;

    public Helper () {
        RoadNetworkBuilder tashkentRoadNetwork = new RoadNetworkBuilder();
        this.roadNetwork = tashkentRoadNetwork.createRoadNetwork("Tashkent");
        this.adjacencyList = AdjacencyListGraphRepresentation.constructGraphRepresentation(this.roadNetwork);
    }

    public void getInputStations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter start station: ");
        String startStation = scanner.nextLine();
        System.out.println("Please enter destination station: ");
        String destinationStation = scanner.nextLine();

        startBsaAlgorithm(startStation, destinationStation);
    }

    public void startBsaAlgorithm(String startStation, String destinationStation) {
        BFS bfsAlgorithm = new BFS();

        Station start = roadNetwork.searchStationByName(startStation);
        Station destination = roadNetwork.searchStationByName(destinationStation);

        if (start != null && destination != null) {
            List<Station> path = bfsAlgorithm.findShortestPath(adjacencyList, start, destination, roadNetwork);
            System.out.println("\nDistance: " + bfsAlgorithm.getDistance());

            displayPath(path);
        }
        else System.out.println("Start or destination station is not found!");
    }

    private void displayPath(List<Station> path) {
        System.out.println("Navigation from start to destination stations: ");

        System.out.println("----------------------------------------------------------------");
        for (Station s : path) {
            if (s.isLineChanged()) {
                System.out.println(s.getName() + " (Change to " + s.getRoadLine().getName() + ")");
                s.setLineChanged(false);
            }
            else System.out.println(s.getName() + " (" + s.getRoadLine().getName() + ")");
        }
        System.out.println("----------------------------------------------------------------");
    }
}
