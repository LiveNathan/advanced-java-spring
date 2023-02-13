package platform.codingnomads.co.springtest.lab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.service.MovieServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringTestLab.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceTest {
    @MockBean
    private MovieServiceImpl movieService;

    // Create test data
    Movie movie = Movie.builder().id(1L).name("The Lord of the Rings").rating(8.9).build();
    Movie movie2 = Movie.builder().id(2L).name("The Fellowship of the Ring").rating(8.2).build();
    List<Movie> movies = Arrays.asList(movie, movie2);

    @Test
    void getAllMoviesTestSuccess() throws Exception {
        when(movieService.getAllMovies()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("The Lord of the Rings", result.get(0).getName());
        assertEquals(8.9, result.get(0).getRating());
    }

    @Test
    void getAllMoviesTestFailure() throws Exception {
        // This test fails exactly for the error that I want it to throw. Why doesn't it work, then?
        when(movieService.getAllMovies()).thenThrow(new IllegalStateException("Failed to get movies"));

        List<Movie> result = movieService.getAllMovies();

        assertTrue(result.toString().contains("Failed to get movies"));
    }

    // Couldn't figure this one out.
//    @Test
//    void deleteAllMovies() {
//        List<Movie> emptyList = new ArrayList<>();
//        when(movieService.deleteAllMovies()).thenReturn(emptyList);
//
//        assertTrue(emptyList.isEmpty());
//    }

    @Test
    void getAllMoviesWithMinimumRating() {
        when(movieService.getAllMoviesWithMinimumRating(anyDouble())).thenReturn(movies);

        List<Movie> result = movieService.getAllMoviesWithMinimumRating(8.9);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}