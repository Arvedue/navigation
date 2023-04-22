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
                                          Station start, Station destination) {
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

        controlChangesBetweenLines(path);

        return path;
    }

    private void controlChangesBetweenLines(List<Station> path) {
        if (path.size() == 2 && path.get(1).isCommonStation()) {
            path.get(1).setChangedDirection(path.get(0).getRoadLine());
            return;
        }

        for (int i = 1; i < path.size() - 1; i++) {
            Station previousStation = path.get(i - 1);
            Station nextStation = path.get(i + 1);
            Station current = path.get(i);

            if (current.isCommonStation()) {
                current.setChangedDirection(nextStation.getRoadLine());

                if (!(previousStation.getRoadLine().equals(nextStation.getRoadLine()))) {
                    current.setDirectionChanged(true);
                }
            }
        }
    }

    public int getDistance() {
        return this.distance;
    }

}
