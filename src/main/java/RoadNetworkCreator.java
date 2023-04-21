import utils.TextFileReader;

import java.util.ArrayList;
import java.util.List;

public class RoadNetworkCreator {
    private final TextFileReader textFileReader;
    private static int id;

    public RoadNetworkCreator() {
        textFileReader = new TextFileReader();
        id = 0;
    }

    public RoadNetwork createRoadNetwork() {
        List<RoadLine> roadLines = loadRoadLines();

        return new RoadNetwork("Tashkent", roadLines);
    }

    public List<RoadLine> loadRoadLines() {
        List<RoadLine> roadLines = new ArrayList<>();
        List<Station> chilonzorStations = loadRoadLineStations("ChilonzorLine.txt");
        List<Station> uzbekistanStations = loadRoadLineStations("UzbekistanLine.txt");
        List<Station> yunusobodStations = loadRoadLineStations("YunusobodLine.txt");

        roadLines.add(new RoadLine("Chilonzor", chilonzorStations));
        roadLines.add(new RoadLine("Uzbekistan", uzbekistanStations));
        roadLines.add(new RoadLine("Yunusobod", yunusobodStations));

        return roadLines;
    }

    public List<Station> loadRoadLineStations(String pathname) {
        List<Station> stations = new ArrayList<>();
        List<String> roadLine = textFileReader.read(pathname);
        for (String station : roadLine) {
            stations.add(new Station(id++, station));
        }

        return stations;
    }
}
