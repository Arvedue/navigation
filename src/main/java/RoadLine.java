import java.util.List;

public class RoadLine {
    private String name;
    private List<Station> stations;

    public RoadLine(String name, List<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", stations=" + stations +
                '}';
    }
}
