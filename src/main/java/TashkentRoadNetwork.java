import utils.TextFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TashkentRoadNetwork {
    private final TextFileReader textFileReader;
    private static int id;

    public TashkentRoadNetwork() {
        id = 0;
        textFileReader = new TextFileReader();
    }

    public RoadNetwork createRoadNetwork() {
        List<RoadLine> roadLines = getRoadLine();
        return new RoadNetwork("Tashkent", roadLines);
    }

    public List<RoadLine> getRoadLine() {
        List<RoadLine> roadLines = new ArrayList<>();
        HashMap<String, Station> allStations = new HashMap<>();
        List<Station> chilonzorStations = getRoadLineStations("ChilonzorLine.txt", allStations);
        List<Station> uzbekistanStations = getRoadLineStations("UzbekistanLine.txt", allStations);
        List<Station> yunusobodStations = getRoadLineStations("YunusobodLine.txt", allStations);

        RoadLine chilonzorRoadLine = new RoadLine("Chilonzor", chilonzorStations);
        RoadLine uzbekistanRoadLine = new RoadLine("Uzbekistan", uzbekistanStations);
        RoadLine yunusobodRoadLine = new RoadLine("Yunusobod", yunusobodStations);

        chilonzorRoadLine.setRoadLineForStations();
        uzbekistanRoadLine.setRoadLineForStations();
        yunusobodRoadLine.setRoadLineForStations();

        roadLines.add(chilonzorRoadLine);
        roadLines.add(uzbekistanRoadLine);
        roadLines.add(yunusobodRoadLine);

        return roadLines;
    }

    public List<Station> getRoadLineStations(String pathname, HashMap<String, Station> allStations) {
        List<Station> stations = new ArrayList<>();
        List<String> data = textFileReader.read(pathname);

        for (String line : data) {
            if (allStations.containsKey(line))
                stations.add(allStations.get(line));
            else {
                Station station = new Station(id++, line);
                allStations.put(line, station);
                stations.add(station);
            }
        }

        return stations;
    }
}
