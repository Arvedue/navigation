import entities.RoadLine;
import entities.RoadNetwork;
import entities.Station;
import utils.Reader;
import utils.TextFileReader;
import utils.TextFilesListReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadNetworkBuilder {

    public RoadNetwork createRoadNetwork(String roadNetworkName) {
        List<RoadLine> roadLines = getRoadLines();

        HashMap<RoadLine, List<Station>> stationsMap = new HashMap<>();
        roadLines.forEach(roadLine -> stationsMap.put(roadLine, roadLine.getStations()));

        return new RoadNetwork(roadNetworkName, roadLines, stationsMap);
    }

    public List<RoadLine> getRoadLines() {
        List<RoadLine> roadLines = new ArrayList<>();
        HashMap<String, Station> allStations = new HashMap<>();

        TextFilesListReader textFilesList = new TextFilesListReader();
        HashMap<String, String> textFiles = textFilesList.getTextFilesList();

        for (Map.Entry<String,String> entry : textFiles.entrySet()) {
            StringBuilder roadLineName = new StringBuilder();
            for (char c : entry.getKey().toCharArray()) {
                if (c != '.') roadLineName.append(c);
                else break;
            }

            List<Station> stations = getRoadLineStations(entry.getValue(), allStations);
            roadLines.add(new RoadLine(roadLineName.toString(), stations));
        }

        return roadLines;
    }

    public List<Station> getRoadLineStations(String pathname, HashMap<String, Station> allStations) {
        if (pathname == null || allStations == null) return null;

        Reader textFileReader = new TextFileReader();
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
