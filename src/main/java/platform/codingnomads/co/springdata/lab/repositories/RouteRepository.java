package platform.codingnomads.co.springdata.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab.models.Area;
import platform.codingnomads.co.springdata.lab.models.Route;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByCode(String code);

    List<Route> findByOrigin(Area origin);
}
