import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;

import java.util.*;

public class BFS {
    // Queue zum Speichern der zu besuchenden Stationen
    private final Queue<Station> queue;

    // HashMap, um den Besuch von Stationen zu verfolgen.
    private final HashMap<Station, Boolean> visited;

    // HashMap, um die vorherigen Stationen zu speichern, um den Pfad am Ende zu erhalten.
    private final HashMap<Station, Station> predecessors;

    // HashMap zum Speichern der Entfernungen vom Start zu anderen Stationen.
    private final HashMap<Station, Integer> distances;

    // Variable zur Speicherung der Entfernung zwischen Start- und Zielstation.
    private int distance;

    public BFS() {
        this.queue = new LinkedList<>();
        this.visited = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.distances = new HashMap<>();
        this.distance = 0;
    }

    /* Methode, die den BFS-Algorithmus verwendet, um den kürzesten Weg von der Start- zur Zielstation zu finden.
    Wir benötigen die AdjacencyList-Darstellung des Graphen, um auf die Stationen und deren Nachbarn zuzugreifen.
    Wir benötigen das Verkehrsnetz, um sicherzugehen, dass die angegebenen Start- und Zielstationen
    im Verkehrsnetz existieren */
    public List<Station> findShortestPath(HashMap<Station, ArrayList<Station>> adjacencyList,
                                          RoadNetwork roadNetwork, Station start, Station destination) {
        if (start == null || destination == null)
            return null;

        if (roadNetwork.notContainsStation(start) || roadNetwork.notContainsStation(destination))
            return null;

        // Hinzufügen der Startstation zur Queue
        queue.offer(start);

        // Markieren Sie die Startstation als besucht in der HashMap
        visited.put(start, true);

        // Hinzufügen der vorherigen Station für die Startstation als Null
        predecessors.put(start, null);

        // Entfernung für die Startstation auf 0 setzen
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            // Entnehmen Sie die erste Station aus der Queue.
            Station currentStation = queue.poll();

            /* Wenn die derzeitige Station gleich dem Ziel ist, bedeutet das,
            dass wir bereits den kürzesten Weg gefunden haben. */
            if (currentStation.equals(destination)) break;

            /* Ermittelt die derzeitige Entfernung aus der HashMap, in der die Entfernungen
            vom Start zu allen Stationen gespeichert sind. */
            int currentDistance = distances.get(currentStation);

            // Schleife durch alle Nachbarstationen der derzeitigen Station.
            for (Station neighbourStation : adjacencyList.get(currentStation)) {

                // Wenn der Nachbarstation bereits besucht ist, wird er ausgelassen.
                if (!visited.containsKey(neighbourStation)) {
                    // wenn die Nachbarstation noch nicht besucht wurde, wird sie in die Queue aufgenommen.
                    queue.offer(neighbourStation);

                    /* Markieren Sie die Nachbarstation als besucht
                    (fügen Sie sie der HashMap hinzu, in der alle besuchten Stationen gespeichert sind). */
                    visited.put(neighbourStation, true);

                    /* Hinzufügen der derzeitigen Station als vorheriger für den Nachbarn zur HashMap hinzu,
                    um den Vorgänger der Station auf dem kürzesten Weg zu verfolgen. */
                    predecessors.put(neighbourStation, currentStation);

                    /* Hinzufügen der Entfernung vom Start zur Nachbarstation in der HashMap,
                    die die Entfernungen vom Start zu allen Stationen speichert. */
                    distances.put(neighbourStation, currentDistance + 1);
                }
            }
        }

        /* Wenn die Zielstation nicht gefunden wird und die Queue leer wird,
        dann gibt es keinen Pfad zwischen der Start- und der Zielstation. */
        if (!visited.containsKey(destination)) return null;

        /* Wenn die Zielstation während des BFS-Traversals gefunden wird,
        konstruieren Sie den kürzesten Weg. */
        List<Station> path = new ArrayList<>();
        Station currentStation = destination;
        while (currentStation != null) {
            path.add(currentStation);
            currentStation = predecessors.get(currentStation);
        }

        distance = distances.get(destination);
        Collections.reverse(path);

        // Außerdem müssen wir die Änderungen zwischen den Zeilen kontrollieren
        if (path.size() >= 1)
            controlChangesBetweenRoadLines(path, roadNetwork);

        return path;
    }

    // Methode, die den Wechsel zwischen den Zeilen kontrolliert
    private void controlChangesBetweenRoadLines(List<Station> path, RoadNetwork roadNetwork) {
        // HashMap, die Linien und alle dazugehörigen Stationen speichert
        HashMap<RoadLine, List<Station>> stationsMap = roadNetwork.getStationsMap();
        List<RoadLine> roadLines = roadNetwork.getLines();

        Station previousStation = null;
        for (int i = 0; i < path.size() - 1; i++) {
            Station currentStation = path.get(i);
            Station nextStation = path.get(i + 1);

            for (RoadLine roadLine : roadLines) {
                List<Station> stations = stationsMap.get(roadLine);

                /* Wenn Linie den aktuellen und die nächste Station enthält,
                dann bedeutet das, dass sie sich auf dieser Linie befinden */
                if (stations.contains(currentStation) && stations.contains(nextStation)) {
                    currentStation.setRoadLine(roadLine);
                    nextStation.setRoadLine(roadLine);

                    /* Wenn die vorherigen Stationen nicht Null sind, dann bedeutet das,
                    dass unser Pfad mehr als 2 Stationen enthält */
                    if (previousStation != null) {
                        /* Wenn die Linie der aktuellen Station nicht gleich der Linie der vorherigen Station ist,
                        dann bedeutet dies, dass die Linie geändert wird. */
                        if (!(currentStation.getRoadLine().equals(previousStation.getRoadLine())))
                            // Markieren Sie in der aktuellen Station, dass wir die Linie wechseln.
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
