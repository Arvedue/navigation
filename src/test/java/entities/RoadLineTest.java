package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadLineTest {

    private RoadLine roadLine;
    private List<Station> stations;
    private Station station1;

    @BeforeEach
    void setUp() {
        station1 = new Station("Station1");

        stations = new ArrayList<>();
        stations.add(station1);

        roadLine = new RoadLine("London", stations);
    }

    @Test
    void givenStationName_whenSearchStationByName_thenReturnStation() {
        Station searchedStation = roadLine.searchStationByName("Station1");
        assertEquals(station1, searchedStation);
    }

    @Test
    void givenNotExistedStationName_whenSearchStationByName_thenReturnNull() {
        Station searchedStation = roadLine.searchStationByName("SomeStation");
        assertNull(searchedStation);
    }

    @Test
    void givenRoadLineName_whenSetName_thenChangeName() {
        roadLine.setName("New York");

        assertNotEquals("London", roadLine.getName());
        assertEquals("New York", roadLine.getName());
    }

    @Test
    void givenStations_whenSetStations_thenSetStationsList() {
        List<Station> stations1 = new ArrayList<>();
        stations1.add(new Station("Station2"));
        roadLine.setStations(stations1);

        List<Station> roadLineStations = roadLine.getStations();
        assertNotEquals(stations.get(0), roadLineStations.get(0));
    }

}