import entities.Station;

import java.util.*;

public class BFS {
    private final Queue<Station> queue;
    private final HashMap<Station, Boolean> visited;
    private final HashMap<Station, Station> predecessors;
    private final HashMap<Station, Integer> distances;
    private int distance;

    public BFS() {
        queue = new LinkedList<>();
        visited = new HashMap<>();
        predecessors = new HashMap<>();
        distances = new HashMap<>();
        distance = 0;
    }

    public List<Station> findShortestPath(HashMap<Station, ArrayList<Station>> adjacencyList, Station start, Station destination) {
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

        if (!visited.containsKey(destination))
            return null;

        List<Station> path = new ArrayList<>();
        Station currentStation = destination;
        while (currentStation != null) {
            path.add(currentStation);
            currentStation = predecessors.get(currentStation);
        }

        distance = distances.get(destination);
        Collections.reverse(path);
        return path;
    }

    public int getDistance() {
        return this.distance;
    }

}
