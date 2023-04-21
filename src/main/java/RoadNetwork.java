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

    @Override
    public String toString() {
        return "RoadNetwork{" +
                "name='" + name + '\'' +
                ", lines=" + roadLines +
                '}';
    }
}
