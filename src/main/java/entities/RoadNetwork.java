package entities;

import java.util.HashMap;
import java.util.List;

public class RoadNetwork {
    private String name;
    private List<RoadLine> roadLines;
    private HashMap<RoadLine, List<Station>> stationsMap;

    public RoadNetwork(String name, List<RoadLine> roadLines, HashMap<RoadLine, List<Station>> stationsMap) {
        this.name = name;
        this.roadLines = roadLines;
        this.stationsMap = stationsMap;
    }

    public HashMap<RoadLine, List<Station>> getStationsMap() {
        return stationsMap;
    }

    public void setStationsMap(HashMap<RoadLine, List<Station>> stationsMap) {
        this.stationsMap = stationsMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoadLine> getLines() {
        return roadLines;
    }

    public void setLines(List<RoadLine> roadLines) {
        this.roadLines = roadLines;
    }

    public Station searchStationByName(String stationName) {
        if (stationName == null) return null;

        for (RoadLine line : roadLines) {
            Station station = line.searchStationByName(stationName);
            if (station != null) return station;
        }

        return null;
    }

    public boolean notContainsStation(Station station) {
        if (station == null) return true;

        for (RoadLine line : roadLines) {
            if (line.getStations().contains(station)) {
                return false;
            }
        }

        return true;
    }

    public void displayRoadNetworkInfo() {
        System.out.println("Name des Verkehrsnetzes : " + this.getName());

        System.out.println("Linien");
        this.getLines().forEach(line -> {
            System.out.println("\nName der Linie: " + line.getName());

            System.out.println("Stations");
            System.out.println("------------------------------------");
            line.getStations().forEach(station -> System.out.println(station.getName()));
            System.out.println("------------------------------------");
        });

    }
}
