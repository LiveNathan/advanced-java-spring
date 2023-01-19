package platform.codingnomads.co.springdata.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.lab.models.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByThoughtsContains(String pattern);
    List<Favorite> findByOrderByRatingDesc();
    boolean existsFavoriteByThoughts(String thought);
}
