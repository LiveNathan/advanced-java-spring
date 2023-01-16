package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.myexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BeerApplication implements CommandLineRunner {
    @Autowired
    BeerRepo beerRepo;

    public static void main(String[] args) {
        SpringApplication.run(BeerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // Add some beers to repo
        Beer laFinDuMonde = Beer.builder().brewery("Unibroue").model("La Fin du Monde").style("Belgian Tripel").abv(9.0).build();
        Beer ominous = Beer.builder().brewery("Bad Weather Brewing Company").model("OMINOUS").style("Brown Ale - Imperial / Double").abv(7.5).build();
        Beer cosmosisHawaiianPunch = Beer.builder().brewery("Venn Brewing Company").model("Cosmosis: Hawaiian Punch").style("Sour - Fruited").abv(9.0).build();
        Beer cosmosisFiveAlive = Beer.builder().brewery("Venn Brewing Company").model("Cosmosis: Five Alive").style("Sour - Fruited").abv(9.0).build();
        Beer guldenDraak = Beer.builder().brewery("Brouwerij Van Steenberge").model("Gulden Draak Classic").style("Belgian Strong Dark Ale").abv(10.5).build();
        Beer scotchAle = Beer.builder().brewery("Barley's Brewing Company").model("Scotch Ale").style("Scottish Ale").abv(8.4).build();
        List<Beer> beers = List.of(laFinDuMonde, ominous, cosmosisFiveAlive, cosmosisHawaiianPunch, guldenDraak, scotchAle);
        beers = beerRepo.saveAllAndFlush(beers);

        /* Demonstrate use of derived methods */
        // Equality
        System.out.println("\n#EQUALITY");
        System.out.println("\n** findByModel **");
        List<Beer> modelLaFin = beerRepo.findByModel("La Fin du Monde");
        modelLaFin.forEach(System.out::println);

        System.out.println("\n** findByStyle **");
        List<Beer> styleBelgianTripel = beerRepo.findByStyle("Belgian Tripel");
        styleBelgianTripel.forEach(System.out::println);

        System.out.println("\n** countByStyle **");
        int countSour = beerRepo.countByStyle("Sour - Fruited");
        System.out.printf("There are %d beers with sour style.%n", countSour);

        // Similarity
        System.out.println("\n#SIMILARITY");
        System.out.println("\n** findByModelContaining **");
        List<Beer> containsModelCosmosis = beerRepo.findByModelContaining("Cosmosis");
        containsModelCosmosis.forEach(System.out::println);

        System.out.println("\n** findByBreweryContaining **");
        List<Beer> containsBreweryVenn = beerRepo.findByBreweryContaining("Venn");
        containsBreweryVenn.forEach(System.out::println);

        // Comparison
        System.out.println("\n#COMPARISON");
        System.out.println("\n** findByAbvLessThan **");
        List<Beer> beersAbvLessThan = beerRepo.findByAbvLessThan(9.0);
        beersAbvLessThan.forEach(System.out::println);

        System.out.println("\n** findByAbvGreaterThan **");
        List<Beer> beersAbvGreaterThan = beerRepo.findByAbvGreaterThan(9.0);
        beersAbvGreaterThan.forEach(System.out::println);

        // Limiting
        System.out.println("\n#LIMITING");
        System.out.println("\n** findFirstByOrderByAbvDesc **");
        Beer topAbv = beerRepo.findFirstByOrderByAbvDesc();
        System.out.println("The beer with highest ABV: " + topAbv);

        System.out.println("\n** findByOrderByAbvDesc **");
        List<Beer> bottomAbv = beerRepo.findByOrderByAbvDesc();
        bottomAbv.forEach(System.out::println);

        System.out.println("\n** findTop2ByStyle **");
        List<Beer> topSour = beerRepo.findTop2ByStyleContaining("Sour");
        topSour.forEach(System.out::println);

        // AND
        System.out.println("\n#AND");
        System.out.println("\n** findByStyleAndAbvGreaterThan **");
        List<Beer> brown = beerRepo.findByStyleContainingAndAbvGreaterThan("Brown", 5.0);
        brown.forEach(System.out::println);

        // Delete all
        beerRepo.deleteAllInBatch();
    }
}
