package platform.codingnomads.co.springdata.example.mybatis.resultsandresult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ResultsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper, MovieMapper movieMapper) {
        return (args) -> {
            //notice the setter names have changed to match Java naming conventions
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbumName("Bon Iver");
            song1.setArtistName("Bon Iver");
            song1.setSongLength(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbumName("Orca");
            song2.setArtistName("Gus Dapperton");
            song2.setSongLength(279);

            songMapper.insertNewSong(song1);
            songMapper.insertNewSong(song2);

            Song song3 = songMapper.getSongById(1L);
            System.out.println(song3.toString());

            // Movies
            Movie movieVacationFriends = Movie.builder().title("Vacation Friends").director("Clay Tarver").rating("R").lengthMinutes(103).build();
            Movie movieM3GAN = Movie.builder().title("M3GAN").director("Gerard Johnstone").rating("PG-13").lengthMinutes(102).build();
            Movie moviePussInBoots = Movie.builder().title("Puss in Boots: The Last Wish").director("Joel Crawford").rating("PG").lengthMinutes(102).build();
            List<Movie> movies = List.of(movieVacationFriends, movieM3GAN, moviePussInBoots);
            for (Movie movie :
                    movies) {
                movieMapper.insertNewMovie(movie);
            }

            // Select by id
            System.out.println("\n** getMovieById **");
            Movie movie = movieMapper.getMovieById(1L);
            System.out.println(movie);

            // Select by title like
            System.out.println("\n** getMoviesWithTitleLike **");
            List<Movie> moviesLikeBoot = movieMapper.getMoviesWithTitleLike("boot");
            moviesLikeBoot.forEach(System.out::println);

            // Update by id
            System.out.println("\n** updateMovie **");
            movie.setLengthMinutes(101);
            movieMapper.updateMovie(movie);

            // Delete all
            System.out.println("\n** deleteAllMovies **");
            movieMapper.deleteAllMovies();
        };
    }
}