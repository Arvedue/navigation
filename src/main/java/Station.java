import java.util.Comparator;

public class Station implements Comparator<Station> {
    private int id;
    private String name;
    private RoadLine roadLine;
    private int cost = 1;

    public Station(){};

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCost() {
        return cost;
    }

    @Override
    public int compare(Station o1, Station o2) {
        return o1.getCost() - o2.getCost();
    }
}
