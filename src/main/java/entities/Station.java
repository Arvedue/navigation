package entities;

public class Station {
    private String name;
    private RoadLine roadLine;
    private RoadLine changedDirection;
    private boolean isCommonStation;
    private boolean isDirectionChanged;

    public Station(String name) {
        this.name = name;
        this.roadLine = null;
        this.changedDirection = null;
        isCommonStation = false;
        isDirectionChanged = false;
    }

    public RoadLine getChangedDirection() {
        return changedDirection;
    }

    public void setChangedDirection(RoadLine changedDirection) {
        this.changedDirection = changedDirection;
    }

    public boolean isDirectionChanged() {
        return isDirectionChanged;
    }

    public void setDirectionChanged(boolean directionChanged) {
        isDirectionChanged = directionChanged;
    }

    public boolean isCommonStation() {
        return isCommonStation;
    }

    public void setCommonStation(boolean commonStation) {
        isCommonStation = commonStation;
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
