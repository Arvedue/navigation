import java.util.List;

public class RoadNetwork {
    private String name;
    private List<RoadLine> roadLines;

    public RoadNetwork(String name, List<RoadLine> roadLines) {
        this.name = name;
        this.roadLines = roadLines;
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
            Station station = line.getStationByName(stationName);
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
            line.getStations().forEach(station -> System.out.println(station.toString()));
        });

    }
}
