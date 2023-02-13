package platform.codingnomads.co.springtest.lab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import platform.codingnomads.co.springtest.lab.controller.MovieController;
import platform.codingnomads.co.springtest.lab.entity.Movie;
import platform.codingnomads.co.springtest.lab.repository.MovieRepository;
import platform.codingnomads.co.springtest.lab.service.MovieServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = SpringTestLab.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// I thought maybe these annotations were necessary for Mockito. Unsure.
//@WebMvcTest(MovieControllerTest.class)
//@ContextConfiguration(classes = SpringTestLab.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieController movieController;

    @SpyBean
    private MovieRepository movieRepository;

    @SpyBean
    private MovieServiceImpl movieService;

    @Test
    public void testGetAllMoviesSuccess() {
        Movie[] movies = testRestTemplate.getForObject("/all", Movie[].class);
        List<Movie> movieList = Arrays.asList(movies);
        assertEquals(2, movieList.size());
        assertEquals(1, movieList.get(0).getId());

        // Similar as above. Just practicing.
        ResponseEntity<Movie[]> responseEntity = testRestTemplate.getForEntity("/all", Movie[].class);
        Movie[] movies1 = responseEntity.getBody();
        List<Movie> movieList1 = Arrays.asList(Objects.requireNonNull(movies1));
        assertEquals(2, movieList1.size());
    }

    @Test
    public void testGetAllMoviesFailure() {
        // DELETE all movies to force failure
        movieService.deleteAllMovies();

        // Try to GET all movies
        Movie[] movies = testRestTemplate.getForObject("/all", Movie[].class);
        List<Movie> movieList = Arrays.asList(movies);
        assertEquals(0, movieList.size());
    }

    @Test
    public void testGetAllMoviesSuccessMockService() throws Exception {
        // Create some mock data
        Movie movie = Movie.builder().name("The Shawshank Redemption").rating(9.3).build();
        Movie movie2 = Movie.builder().name("The Pursuit of Happyness").rating(8.0).build();
        ArrayList<Movie> movies = new ArrayList<>(Arrays.asList(movie, movie2));

        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].rating").value(9.3))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"))
                .andExpect(jsonPath("$[1].rating").value(8.0));
    }

    @Test
    public void testGetAllMoviesSuccessVariation2() throws Exception {
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].rating").value(9.3))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"))
                .andExpect(jsonPath("$[1].rating").value(8.0));
    }

    @Test
    public void testGetAllMoviesWithMinimumRatingSuccess() throws Exception {
        Double minRating = 4.5;
        List<Movie> expectedMovies = Arrays.asList(new Movie(1L, "Movie1", 4.6), new Movie(2L, "Movie2", 4.8));
        // What I'm learning here is that to test the Movie Controller I actually mock the Movie Service, then perform a GET request on the controller enpoint.
        when(movieService.getAllMoviesWithMinimumRating(minRating)).thenReturn(expectedMovies);

        mockMvc.perform(get("/all/{minRating}", minRating))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Movie1"))
                .andExpect(jsonPath("$[0].rating").value(4.6))
                .andExpect(jsonPath("$[1].name").value("Movie2"))
                .andExpect(jsonPath("$[1].rating").value(4.8));
    }

    @Test
    public void testGetAllMoviesWithMinimumRatingFailure() throws Exception {
        // This test isn't passing because it's throwing the exact exception that it's supposed to throw. Not sure what's missing.
        when(movieService.getAllMoviesWithMinimumRating(anyDouble())).thenThrow(new IllegalArgumentException("minRating must be greater than 0"));
        mockMvc.perform(get("/all/{minRating}", 0.0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("minRating must be greater than 0"));
    }


}
