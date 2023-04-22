package entities;

public class Station {
    private String name;
    private RoadLine roadLine;
    private boolean isLineChanged;

    public Station(String name) {
        this.name = name;
        this.roadLine = null;
        this.isLineChanged = false;
    }

    public boolean isLineChanged() {
        return isLineChanged;
    }

    public void setLineChanged(boolean lineChanged) {
        isLineChanged = lineChanged;
    }

    public RoadLine getRoadLine() {
        return roadLine;
    }

    public void setRoadLine(RoadLine roadLine) {
        this.roadLine = roadLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
