package platform.codingnomads.co.springdata.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.lab.models.Area;
import platform.codingnomads.co.springdata.lab.models.Route;
import platform.codingnomads.co.springdata.lab.repositories.AreaRepository;
import platform.codingnomads.co.springdata.lab.repositories.RouteRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataLab implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;

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

        // Select
        System.out.println("\n** findByOrigin **");
        List<Route> routesOriginA = routeRepository.findByOrigin(areaRepository.findByCode("A"));
        routesOriginA.forEach(System.out::println);
    }
}
