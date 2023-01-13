package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.myexample;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeerRepo extends JpaRepository<Beer, Long> {
    // Equality
    List<Beer> findByModel(String name);

    List<Beer> findByStyle(String style);

    int countByStyle(String style);

    // Similarity
    List<Beer> findByModelContaining(String pattern);

    List<Beer> findByBreweryContaining(String pattern);

    // Comparison
    List<Beer> findByAbvLessThan(double abv);

    List<Beer> findByAbvGreaterThan(double abv);

    // Limiting
    Beer findFirstByOrderByAbvDesc();  // Sorting With the OrderBy Method Keyword

    List<Beer> findByOrderByAbvDesc();

    List<Beer> findTop2ByStyleContaining(String style);

    // AND
    List<Beer> findByStyleContainingAndAbvGreaterThan(String style, double abv);
}
