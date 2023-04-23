package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    private Station station;

    @BeforeEach
    void setUp() {
        station = new Station("Paris");
    }

    @Test
    void givenName_whenSetName_thenChangeName() {
        String oldName = station.getName();
        station.setName("Berlin");

        assertNotEquals(oldName, station.getName());
        assertEquals("Berlin", station.getName());
    }

    @Test
    void givenBooleanLineChanged_whenSetLineChanged_thenChangeValue() {
        station.setLineChanged(true);

        assertTrue(station.isLineChanged());
    }

    @Test
    void givenRoadLine_whenSetRoadLine_thenSetRoadLine() {
        RoadLine roadLine = new RoadLine("New York", new ArrayList<>());
        station.setRoadLine(roadLine);

        assertEquals(roadLine, station.getRoadLine());
    }

}