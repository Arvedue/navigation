import entities.RoadNetwork;
import entities.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Helper {

    private final RoadNetwork roadNetwork;
    private final HashMap<Station, ArrayList<Station>> adjacencyList;

    public Helper () {
        TashkentRoadNetworkBuilder tashkentRoadNetwork = new TashkentRoadNetworkBuilder();
        this.roadNetwork = tashkentRoadNetwork.createRoadNetwork();

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
            List<Station> path = bfsAlgorithm.findShortestPath(adjacencyList, start, destination);
            System.out.println("Distance: " + bfsAlgorithm.getDistance());

            displayPath(path);
        }
        else System.out.println("Start or destination station is not found!");
    }

    private void displayPath(List<Station> path) {
        System.out.println("Navigation from start to destination stations: ");

        for (Station s : path) {
            if (s.isDirectionChanged()) {
                System.out.println(s.getName() + " (Change to " + s.getChangedDirection().getName() + " Line)");
                s.setDirectionChanged(false);
            }
            else {
                if (!s.isCommonStation())
                    System.out.println(s.getName() + " (" + s.getRoadLine().getName() + " Line)");
                else
                    System.out.println(s.getName() + " (" + s.getChangedDirection().getName() + " Line)");
            }
        }
    }
}