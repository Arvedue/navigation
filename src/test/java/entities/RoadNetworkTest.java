package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadNetworkTest {

    private RoadNetwork roadNetwork;
    private List<RoadLine> roadLines;
    private Station station1;
    private HashMap<RoadLine, List<Station>> stationsMap;

    @BeforeEach
    void setUp() {
        roadLines = new ArrayList<>();
        List<Station> stations = new ArrayList<>();
        station1 = new Station("Station1");
        stations.add(station1);
        RoadLine roadLine = new RoadLine("Paris", stations);
        roadLines.add(roadLine);
        stationsMap = new HashMap<>();
        stationsMap.put(roadLine, stations);

        roadNetwork = new RoadNetwork("Budapest", roadLines, stationsMap);
    }

    @Test
    void givenName_whenSetName_thenChangeName() {
        roadNetwork.setName("Madrid");

        assertEquals("Madrid", roadNetwork.getName());
    }

    @Test
    void givenStationsMap_whenSetStationsMap_thenSetMap() {
        HashMap<RoadLine, List<Station>> stationsMap = new HashMap<>();
        roadNetwork.setStationsMap(stationsMap);

        assertEquals(stationsMap, roadNetwork.getStationsMap());
    }

    @Test
    void givenRoadLines_whenSetRoadLines_thenSetLines() {
        List<RoadLine> roadLines = new ArrayList<>();
        roadNetwork.setLines(roadLines);

        assertEquals(roadLines, roadNetwork.getLines());
    }

    @Test
    void givenNullName_whenSearchStationByName_thenReturnNull() {
        Station station = roadNetwork.searchStationByName(null);

        assertNull(station);
    }

    @Test
    void givenNotExistedStationName_whenSearchStationByName_thenReturnNull() {
        roadNetwork.setLines(roadLines);

        Station station = roadNetwork.searchStationByName("Station2");
        assertNull(station);
    }

    @Test
    void givenStationName_whenSearchStationByName_thenReturnStation() {
        roadNetwork.setLines(roadLines);

        Station station = roadNetwork.searchStationByName("Station1");
        assertEquals(station1, station);
    }

    @Test
    void givenStationsMap_whenSetStationsMap_thenChangeStationsMap() {
        roadNetwork.setStationsMap(new HashMap<>());

        assertNotEquals(stationsMap, roadNetwork.getStationsMap());
    }

    @Test
    void givenNullStation_whenNotContainsStation_thenReturnNull() {
        boolean notContainsStation = roadNetwork.notContainsStation(null);

        assertTrue(notContainsStation);
    }

    @Test
    void givenExistedStation_whenNotContainsStation_thenReturnFalse() {
        boolean notContainsStation = roadNetwork.notContainsStation(station1);

        assertFalse(notContainsStation);
    }

    @Test
    void givenNotExistedStation_whenNotContainsStation_thenReturnTrue() {
        boolean notContainsStation = roadNetwork.notContainsStation(new Station("Station2"));

        assertTrue(notContainsStation);
    }

}