import entities.RoadNetwork;
import entities.Station;

import java.util.*;

public class Helper {
    private final Scanner scanner;

    public Helper() {
        this.scanner = new Scanner(System.in);
    }

    public void startProgram() {
        DataPreparation dataPreparation = new DataPreparation();

        boolean end = true;
        while (end) {
            getInputStations(dataPreparation.getRoadNetwork(), dataPreparation.getAdjacencyList());
            System.out.println("\nContinue? (yes / no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("NO")) end = false;
        }
    }

    private void getInputStations(RoadNetwork roadNetwork, HashMap<Station, ArrayList<Station>> adjacencyList) {
        if (roadNetwork == null || adjacencyList == null) return;

        System.out.println("Please enter start station: ");
        String startStation = scanner.nextLine();
        System.out.println("Please enter destination station: ");
        String destinationStation = scanner.nextLine();

        startBfsAlgorithm(roadNetwork, adjacencyList, startStation, destinationStation);
    }


    private void startBfsAlgorithm(RoadNetwork roadNetwork, HashMap<Station, ArrayList<Station>> adjacencyList,
                                  String startStation, String destinationStation) {

        if (startStation == null || destinationStation == null) return;

        BFS bfsAlgorithm = new BFS();

        Station start = roadNetwork.searchStationByName(startStation);
        Station destination = roadNetwork.searchStationByName(destinationStation);

        List<Station> path = bfsAlgorithm.findShortestPath(adjacencyList, roadNetwork, start, destination);
        if (path != null) {
            System.out.println("\nDistance: " + bfsAlgorithm.getDistance());
            displayPath(path);
        }
        else System.out.println("Path not found!");
    }

    private void displayPath(List<Station> path) {
        if (path == null) return;

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
