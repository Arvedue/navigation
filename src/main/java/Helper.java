import entities.RoadNetwork;
import entities.Station;

import java.util.*;

// Klasse, die beim Start des Programms hilft
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
            System.out.println("\nWeiterlaufen? (Ja / Nein): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("NEIN")) end = false;
        }
    }

    private void getInputStations(RoadNetwork roadNetwork, HashMap<Station, ArrayList<Station>> adjacencyList) {
        if (roadNetwork == null || adjacencyList == null) return;

        System.out.println("\nStart: ");
        String startStation = scanner.nextLine();
        System.out.println("Ziel: ");
        String destinationStation = scanner.nextLine();

//        roadNetwork.displayRoadNetworkInfo();

        startBfsAlgorithm(roadNetwork, adjacencyList, startStation, destinationStation);
    }


    private void startBfsAlgorithm(RoadNetwork roadNetwork, HashMap<Station, ArrayList<Station>> adjacencyList,
                                  String startStation, String destinationStation) {
        BFS bfsAlgorithm = new BFS();

        Station start = roadNetwork.searchStationByName(startStation);
        Station destination = roadNetwork.searchStationByName(destinationStation);

        List<Station> path = bfsAlgorithm.findShortestPath(adjacencyList, roadNetwork, start, destination);
        if (path != null) {
            System.out.println("\nEntfernung: " + bfsAlgorithm.getDistance());
            displayPath(path);
        }
        else System.out.println("Pfad nicht gefunden!");
    }

    private void displayPath(List<Station> path) {
        if (path == null) return;

        System.out.println("Navigation von Start- zu Zielstationen: ");

        System.out.println("----------------------------------------------------------------");
        for (Station s : path) {
            if (s.isLineChanged()) {
                System.out.println(s.getName() + " (Wechseln zur " + s.getRoadLine().getName() + ")");
                s.setLineChanged(false);
            }
            else System.out.println(s.getName() + " (" + s.getRoadLine().getName() + ")");
        }
        System.out.println("----------------------------------------------------------------");
    }

}
