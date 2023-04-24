import entities.RoadNetwork;
import entities.Station;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    private static RoadNetwork roadNetwork;
    private static HashMap<Station, ArrayList<Station>> adjacencyList;

    private Station startStation;
    private Station destinationStation;
    private List<Station> path;

    @BeforeAll
    static void setUp() {
        DataPreparation dataPreparation = new DataPreparation();
        roadNetwork = dataPreparation.getRoadNetwork();
        adjacencyList = dataPreparation.getAdjacencyList();
    }

    @Test
    @DisplayName("When start station is Invalid")
    void givenInvalidStartStation_whenFindShortestPath_thenReturnNull() {
        BFS algorithm = new BFS();
        startStation = new Station("Station");
        destinationStation = roadNetwork.searchStationByName("Pushkin");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertNull(path);
    }

    @Test
    @DisplayName("When destination station is Invalid")
    void givenInvalidDestinationStation_whenFindShortestPath_thenReturnNull() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Pushkin");
        destinationStation = new Station("Station");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertNull(path);
    }

    @Test
    @DisplayName("When start & destination stations are Invalid")
    void givenBothInvalidStartDestinationStations_whenFindShortestPath_thenReturnNull() {
        BFS algorithm = new BFS();
        startStation = new Station("Station1");
        destinationStation = new Station("Station2");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertNull(path);
    }

    @Test
    @DisplayName("When start & destination stations are Null")
    void givenBothStartDestinationStationsNull_whenFindShortestPath_thenReturnNull() {
        BFS algorithm = new BFS();
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, null, null);

        assertNull(path);
    }

    @Test
    @DisplayName("When start:Toshkent, destination: Toshkent")
    void givenStartToshkent_destinationToshkent_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Toshkent");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, startStation);

        assertEquals(roadNetwork.searchStationByName("Toshkent"), path.get(0));
    }

    @Test
    @DisplayName("When start:Ming Urik - Oybek, destination: Ming Urik - Oybek")
    void givenStartMingUrikOybek_destinationMingUrikOybek_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Ming Urik - Oybek");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, startStation);

        assertEquals(roadNetwork.searchStationByName("Ming Urik - Oybek"), path.get(0));
    }

    @Test
    @DisplayName("When start:Toshkent, destination:Pushkin")
    void givenStartToshkent_DestinationPushkin_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Toshkent");
        destinationStation = roadNetwork.searchStationByName("Pushkin");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(4, algorithm.getDistance());
        assertEquals(5, path.size());
        assertEquals(roadNetwork.searchStationByName("Toshkent"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Ming Urik - Oybek"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Hamid Olimjon"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Pushkin"), path.get(4));
    }

    @Test
    @DisplayName("When start:Dustlik, destination:Beruni")
    void givenStartBeruni_DestinationDustlik_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Dustlik");
        destinationStation = roadNetwork.searchStationByName("Beruni");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(10, algorithm.getDistance());
        assertEquals(11, path.size());
        assertEquals(roadNetwork.searchStationByName("Dustlik"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Mashinasozlar"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Toshkent"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Ming Urik - Oybek"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Kosmonavtlar"), path.get(4));
        assertEquals(roadNetwork.searchStationByName("Uzbekistan"), path.get(5));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(6));
        assertEquals(roadNetwork.searchStationByName("Gafur Gulom"), path.get(7));
        assertEquals(roadNetwork.searchStationByName("Chorsu"), path.get(8));
        assertEquals(roadNetwork.searchStationByName("Tinchlik"), path.get(9));
        assertEquals(roadNetwork.searchStationByName("Beruni"), path.get(10));
    }

    @Test
    @DisplayName("When start:Buyuk Ipak Yuli, destination:Olmazar")
    void givenStartBuyukIpakYuli_DestinationOlmazar_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Buyuk Ipak Yuli");
        destinationStation = roadNetwork.searchStationByName("Olmazar");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(11, algorithm.getDistance());
        assertEquals(12, path.size());
        assertEquals(roadNetwork.searchStationByName("Buyuk Ipak Yuli"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Pushkin"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Hamid Olimjon"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Mustaqilliq Maidoni"), path.get(4));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(5));
        assertEquals(roadNetwork.searchStationByName("Bunyodkor"), path.get(6));
        assertEquals(roadNetwork.searchStationByName("Milliy Bog"), path.get(7));
        assertEquals(roadNetwork.searchStationByName("Novza"), path.get(8));
        assertEquals(roadNetwork.searchStationByName("Mirzo Ulugbek"), path.get(9));
        assertEquals(roadNetwork.searchStationByName("Chilonzor"), path.get(10));
        assertEquals(roadNetwork.searchStationByName("Olmazar"), path.get(11));
    }

    @Test
    @DisplayName("When start:Shahriston, destination:Ming Urik - Oybek")
    void givenStartShahriston_DestinationMingUrikOybek_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Shahriston");
        destinationStation = roadNetwork.searchStationByName("Ming Urik - Oybek");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(5, algorithm.getDistance());
        assertEquals(6, path.size());
        assertEquals(roadNetwork.searchStationByName("Shahriston"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Bodomzor"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Minor"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Abdulla Qodirii"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(4));
        assertEquals(roadNetwork.searchStationByName("Ming Urik - Oybek"), path.get(5));
    }

    @Test
    @DisplayName("When start:Bunyodkor, destination:Gafur Gulom")
    void givenStartBunyodkor_DestinationGafurGulom_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Bunyodkor");
        destinationStation = roadNetwork.searchStationByName("Gafur Gulom");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(2, algorithm.getDistance());
        assertEquals(3, path.size());
        assertEquals(roadNetwork.searchStationByName("Bunyodkor"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Gafur Gulom"), path.get(2));
    }

    @Test
    @DisplayName("When start:Bunyodkor, destination:Uzbekistan")
    void givenStartBunyodkor_DestinationUzbekistan_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Bunyodkor");
        destinationStation = roadNetwork.searchStationByName("Uzbekistan");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(2, algorithm.getDistance());
        assertEquals(3, path.size());
        assertEquals(roadNetwork.searchStationByName("Bunyodkor"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Uzbekistan"), path.get(2));
    }

    @Test
    @DisplayName("When start:Bunyodkor, destination:Abdulla Qodirii")
    void givenStartBunyodkor_DestinationAbdullaQodirii_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Bunyodkor");
        destinationStation = roadNetwork.searchStationByName("Abdulla Qodirii");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(4, algorithm.getDistance());
        assertEquals(5, path.size());
        assertEquals(roadNetwork.searchStationByName("Bunyodkor"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Mustaqilliq Maidoni"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Abdulla Qodirii"), path.get(4));
    }

    @Test
    @DisplayName("When start:Abdulla Qodirii, destination:Uzbekistan")
    void givenStartAbdullaQodirii_DestinationUzbekistan_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Abdulla Qodirii");
        destinationStation = roadNetwork.searchStationByName("Uzbekistan");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(4, algorithm.getDistance());
        assertEquals(5, path.size());
        assertEquals(roadNetwork.searchStationByName("Abdulla Qodirii"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Mustaqilliq Maidoni"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Paxtakor - Alisher Navoiy"), path.get(3));
        assertEquals(roadNetwork.searchStationByName("Uzbekistan"), path.get(4));
    }

    @Test
    @DisplayName("When start:Abdulla Qodirii, destination:Kosmonavtlar")
    void givenStartAbdullaQodirii_DestinationKosmonavtlar_whenFindShortestPath_thenReturnPath() {
        BFS algorithm = new BFS();
        startStation = roadNetwork.searchStationByName("Abdulla Qodirii");
        destinationStation = roadNetwork.searchStationByName("Kosmonavtlar");
        path = algorithm.findShortestPath(adjacencyList, roadNetwork, startStation, destinationStation);

        assertEquals(3, algorithm.getDistance());
        assertEquals(4, path.size());
        assertEquals(roadNetwork.searchStationByName("Abdulla Qodirii"), path.get(0));
        assertEquals(roadNetwork.searchStationByName("Amir Temur Xiyoboni - Yunus Rajabiy"), path.get(1));
        assertEquals(roadNetwork.searchStationByName("Ming Urik - Oybek"), path.get(2));
        assertEquals(roadNetwork.searchStationByName("Kosmonavtlar"), path.get(3));
    }

}