package platform.codingnomads.co.springdata.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.lab.models.Area;
import platform.codingnomads.co.springdata.lab.models.Favorite;
import platform.codingnomads.co.springdata.lab.models.Route;
import platform.codingnomads.co.springdata.lab.repositories.AreaRepository;
import platform.codingnomads.co.springdata.lab.repositories.FavoriteRepository;
import platform.codingnomads.co.springdata.lab.repositories.RouteRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;
    private final FavoriteRepository favoriteRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataLab.class);
    }

    @Override
    public void run(String... args) throws Exception {

        // Insert areas
        final List<Area> areas = Arrays.asList(
                Area.builder().code("G").build(),
                Area.builder().code("H").build(),
                Area.builder().code("Y").build(),
                Area.builder().code("Z").build(),
                Area.builder().code("A").build(),
                Area.builder().code("B").build(),
                Area.builder().code("C").build(),
                Area.builder().code("D").build()
        );
        for (Area area : areas) {  // For each area
            if (areaRepository.findByCode(area.getCode()) == null) {  // Is it already in the DB?
                areaRepository.save(area);  // If not, save it.
            }
        }

        // persist several routes into the database
        Route routeYZ = new Route(areaRepository.findByCode("Y"), areaRepository.findByCode("Z"));
        Route routeAB = new Route(areaRepository.findByCode("A"), areaRepository.findByCode("B"));
        Route routeAC = new Route(areaRepository.findByCode("A"), areaRepository.findByCode("C"));
        Route routeBZ = new Route(areaRepository.findByCode("B"), areaRepository.findByCode("Z"));
        List<Route> routes = List.of(routeYZ, routeAB, routeAC, routeBZ);
        for (Route route : routes) {  // For each route
            if (routeRepository.findByCode(route.getCode()) == null) {  // Is it already in the DB?
                routeRepository.save(route);  // If not, save it.
            }
        }

        // Declare derived query methods to retrieve all Routes that begin or end with a specific Area.
        System.out.println("\n** findByOrigin **");
        List<Route> routesOriginA = routeRepository.findByOrigin(areaRepository.findByCode("A"));
        routesOriginA.forEach(System.out::println);

        // Declare a derived query method to retrieve a specific Route by code.
        System.out.println("\n** findByCode **");
        Route route = routeRepository.findByCode("Y-Z");
        System.out.println(route);

        // Create a third entity, with a relationship to both Area and Route, along with a repository containing relevant methods. Persist some of these entities to the database. Then, using your retrieval methods proceed to pull some data and print it to the console.
        Favorite favorite0 = Favorite.builder().area(areaRepository.findByCode("A")).rating((byte) 4).thoughts("I really love it at A.").build();
        Favorite favorite1 = Favorite.builder().area(areaRepository.findByCode("B")).rating((byte) 1).thoughts("I really hate it at B.").build();
        Favorite favorite2 = Favorite.builder().route(routeYZ).rating((byte) 3).thoughts("I feel neither love nor hate for route Y-Z").build();
        Favorite favorite3 = Favorite.builder().route(routeBZ).rating((byte) 5).thoughts("BEST route ever!").build();
        List<Favorite> favorites = List.of(favorite0, favorite1, favorite2, favorite3);
        for (Favorite favorite : favorites) {  // For each favorite
            if (!favoriteRepository.existsFavoriteByThoughts(favorite.getThoughts())) {  // Is it already in the DB?
                favoriteRepository.save(favorite);  // If not, save it.
            }
        }

        System.out.println("\n** findByThoughtsContains **");
        List<Favorite> favorites1 = favoriteRepository.findByThoughtsContains("love");
        favorites1.forEach(System.out::println);

        System.out.println("\n** findByOrderByRatingDesc **");
        List<Favorite> favorites2 = favoriteRepository.findByOrderByRatingDesc();
        favorites2.forEach(System.out::println);

        // Delete
        favoriteRepository.deleteAllInBatch();
        routeRepository.deleteAllInBatch();
        areaRepository.deleteAllInBatch();
    }


}
