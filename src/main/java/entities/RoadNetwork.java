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
        for (RoadLine line : roadLines) {
            Station station = line.searchStationByName(stationName);
            if (station != null) return station;
        }

        return null;
    }

    public void displayRoadNetworkInfo() {
        System.out.println("Road Network name: " + this.getName());

        System.out.println("Lines");
        this.getLines().forEach(line -> {
            System.out.println("\nLine name: " + line.getName());

            System.out.println("Stations");
            System.out.println("------------------------------------");
            line.getStations().forEach(station -> System.out.println(station.getName()));
            System.out.println("------------------------------------");
        });

    }
}
