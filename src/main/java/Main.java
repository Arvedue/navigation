import utils.TextFileReader;

public class Main {

    public static void main(String[] args) {
        RoadNetworkCreator roadNetworkCreator = new RoadNetworkCreator();
        RoadNetwork tashkent = roadNetworkCreator.createRoadNetwork();

        System.out.println("Road Network name: " + tashkent.getName());
        System.out.println("Lines");
        tashkent.getLines().forEach(line -> {
            System.out.println("Line name: " + line.getName());
            System.out.println("Stations");
            line.getStations().forEach(station -> System.out.println(station.toString()));
        });

    }
}
