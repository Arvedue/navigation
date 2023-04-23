import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;

import java.util.*;

public class BFS {
    private final Queue<Station> queue;
    private final HashMap<Station, Boolean> visited;
    private final HashMap<Station, Station> predecessors;
    private final HashMap<Station, Integer> distances;
    private int distance;

    public BFS() {
        this.queue = new LinkedList<>();
        this.visited = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.distances = new HashMap<>();
        this.distance = 0;
    }

    public List<Station> findShortestPath(HashMap<Station, ArrayList<Station>> adjacencyList,
                                          RoadNetwork roadNetwork, Station start, Station destination) {
        if (start == null || destination == null)
            return null;

        if (roadNetwork.notContainsStation(start) || roadNetwork.notContainsStation(destination))
            return null;

        queue.offer(start);
        visited.put(start, true);
        predecessors.put(start, null);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            Station currentStation = queue.poll();
            if (currentStation.equals(destination)) break;

            int currentDistance = distances.get(currentStation);

            for (Station neighbourStation : adjacencyList.get(currentStation)) {
                if (!visited.containsKey(neighbourStation)) {
                    queue.offer(neighbourStation);
                    visited.put(neighbourStation, true);
                    predecessors.put(neighbourStation, currentStation);
                    distances.put(neighbourStation, currentDistance + 1);
                }
            }
        }

        if (!visited.containsKey(destination)) return null;

        List<Station> path = new ArrayList<>();
        Station currentStation = destination;
        while (currentStation != null) {
            path.add(currentStation);
            currentStation = predecessors.get(currentStation);
        }

        distance = distances.get(destination);
        Collections.reverse(path);

        controlChangesBetweenRoadLines(path, roadNetwork);

        return path;
    }

    private void controlChangesBetweenRoadLines(List<Station> path, RoadNetwork roadNetwork) {
        HashMap<RoadLine, List<Station>> stationsMap = roadNetwork.getStationsMap();
        List<RoadLine> roadLines = roadNetwork.getLines();

        Station previousStation = null;
        for (int i = 0; i < path.size() - 1; i++) {
            Station currentStation = path.get(i);
            Station nextStation = path.get(i + 1);

            for (RoadLine roadLine : roadLines) {
                List<Station> stations = stationsMap.get(roadLine);
                if (stations.contains(currentStation) && stations.contains(nextStation)) {
                    currentStation.setRoadLine(roadLine);
                    nextStation.setRoadLine(roadLine);

                    if (previousStation != null) {
                        if (!(currentStation.getRoadLine().equals(previousStation.getRoadLine())))
                            currentStation.setLineChanged(true);
                    }
                }
            }

            previousStation = currentStation;
        }
    }

    public int getDistance() {
        return this.distance;
    }

}
