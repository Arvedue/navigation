import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;
import utils.Reader;
import utils.TextFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TashkentRoadNetworkBuilder {
    private final Reader textFileReader;

    public TashkentRoadNetworkBuilder() {
        textFileReader = new TextFileReader();
    }

    public RoadNetwork createRoadNetwork() {
        List<RoadLine> roadLines = getRoadLines();

        HashMap<RoadLine, List<Station>> stationsMap = new HashMap<>();
        roadLines.forEach(roadLine -> stationsMap.put(roadLine, roadLine.getStations()));

        return new RoadNetwork("Tashkent", roadLines, stationsMap);
    }

    public List<RoadLine> getRoadLines() {
        List<RoadLine> roadLines = new ArrayList<>();
        HashMap<String, Station> allStations = new HashMap<>();
        List<Station> chilonzorStations = getRoadLineStations("ChilonzorLine.txt", allStations);
        List<Station> uzbekistanStations = getRoadLineStations("UzbekistanLine.txt", allStations);
        List<Station> yunusobodStations = getRoadLineStations("YunusobodLine.txt", allStations);

        RoadLine yunusobodRoadLine = new RoadLine("Yunusobod", yunusobodStations);
        RoadLine uzbekistanRoadLine = new RoadLine("Uzbekistan", uzbekistanStations);
        RoadLine chilonzorRoadLine = new RoadLine("Chilonzor", chilonzorStations);

        roadLines.add(chilonzorRoadLine);
        roadLines.add(uzbekistanRoadLine);
        roadLines.add(yunusobodRoadLine);

        return roadLines;
    }

    public List<Station> getRoadLineStations(String pathname, HashMap<String, Station> allStations) {
        List<Station> stations = new ArrayList<>();
        List<String> data = textFileReader.read(pathname);

        for (String line : data) {
            if (allStations.containsKey(line)) {
                stations.add(allStations.get(line));
            }
            else {
                Station station = new Station(line);
                allStations.put(line, station);
                stations.add(station);
            }
        }

        return stations;
    }
}
