package entities;

public class Station {
    private int id;
    private String name;
    private RoadLine roadLines;

    public Station(){};

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoadLine getRoadLine() {
        return roadLines;
    }

    public void setRoadLine(RoadLine roadLine) {
        this.roadLines = roadLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
