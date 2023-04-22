import entities.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    private final PriorityQueue<Station> pq;
    private final HashMap<Station, Integer> distances;
    private final HashMap<Station, Station> previousStations;
    private final List<Station> path;

    public DijkstraAlgorithm() {
        this.pq = new PriorityQueue<>(new Station());
        this.distances = new HashMap<>();
        this.previousStations = new HashMap<>();
        this.path = new ArrayList<>();
    }

    public List<Station> findShortestPath(HashMap<Station, ArrayList<Station>> adjacencyList, Station source, Station destination) {
        for (Station station : adjacencyList.keySet()) {
            distances.put(station, Integer.MAX_VALUE);
            previousStations.put(station, null);
        }

        distances.put(source, 0);
        pq.offer(source);

        while (!pq.isEmpty()) {
            Station currentStation = pq.poll();

            if (currentStation.equals(destination)) break;

            int currentDistance = distances.get(currentStation);
            for (Station neighbourStation : adjacencyList.get(currentStation)) {
                int newDistance = currentDistance + 1;
                if (newDistance < distances.get(neighbourStation)) {
                    distances.put(neighbourStation, newDistance);
                    previousStations.put(neighbourStation, currentStation);
                    pq.add(neighbourStation);
                }
            }
        }

        Station currentStation = destination;
        while (currentStation != null) {
            path.add(0, currentStation);
            currentStation = previousStations.get(currentStation);
        }

        return path;
    }

}
