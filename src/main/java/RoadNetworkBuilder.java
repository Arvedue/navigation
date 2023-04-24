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

// Klasse zur Erstellung von Verkehrsnetz mit Linien und Stationen
public class RoadNetworkBuilder {

    // Methode zur Erstellung eines Verkehrsnetz-Objekts
    public RoadNetwork createRoadNetwork(String roadNetworkName) {
        List<RoadLine> roadLines = getRoadLines();

        HashMap<RoadLine, List<Station>> stationsMap = new HashMap<>();
        roadLines.forEach(roadLine -> stationsMap.put(roadLine, roadLine.getStations()));

        return new RoadNetwork(roadNetworkName, roadLines, stationsMap);
    }

    // Methode zur Erstellung von Linien
    private List<RoadLine> getRoadLines() {
        List<RoadLine> roadLines = new ArrayList<>();

        /* HashMap speichert den Namen der Station und das zugehörige Stations objekt.
        Es gibt einige Stationen, die sich auf mehr als einer Linie befinden,
        weil sie als Kreuzung zwischen Linien fungieren.
        Wenn das Stations objekt mit dem bestimmten Namen bereits existiert,
        wird kein neues Stations objekt erstellt, sondern dieses Objekt aus der HashMap geholt */
        HashMap<String, Station> allStations = new HashMap<>();

        /* Die Klasse TextFilesListReader enthält eine Methode zum Auslesen aller
        textFiles, die eine Linie mit allen ihren Stationen repräsentieren */
        TextFilesListReader textFilesList = new TextFilesListReader();
        // HashMap speichert den Dateinamen als Schlüssel und den Pfad als Wert.
        HashMap<String, String> textFiles = textFilesList.getTextFilesList();

        // Schleife durch alle Textdateien im "lines"-Verzeichnis
        for (Map.Entry<String,String> entry : textFiles.entrySet()) {
            /* Ermittlung des Linien-Namens aus dem textFile-Namen.
            Da textFile-Namen als 'Dateiname.txt' gespeichert werden,
            müssen wir nur Werte erhalten, die vor einem Punkt stehen */
            StringBuilder roadLineName = new StringBuilder();
            for (char c : entry.getKey().toCharArray()) {
                if (c != '.') roadLineName.append(c);
                else break;
            }

            /* Ermittlung aller Stationen aus textFiles.
            Jede Zeile in der textFile repräsentiert eine Station. */
            List<Station> stations = getStations(entry.getValue(), allStations);

            RoadLine roadLine = new RoadLine(roadLineName.toString(), stations);
            stations.forEach(s -> s.setRoadLine(roadLine));
            roadLines.add(roadLine);
        }

        return roadLines;
    }

    // Methode zur Erstellung von Stationen
    private List<Station> getStations(String pathname, HashMap<String, Station> allStations) {
        if (pathname == null || allStations == null) return null;

        /* Hier lesen wir alle Zeilen aus den textFiles.
        Jede Zeile in der textFile repräsentiert eine Station */
        Reader textFileReader = new TextFileReader();
        List<Station> stations = new ArrayList<>();
        List<String> data = textFileReader.read(pathname);

        // Hier wird geprüft, ob das Stations objekt mit einem bestimmten Namen bereits existiert.
        for (String line : data) {
            /* Wenn das Stations objekt bereits existiert, holen wir dieses Objekt aus der HashMap.
            Andernfalls wird ein neues Stations objekt erstellt und hinzugefügt */
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
