package platform.codingnomads.co.springtest.lab.service;

import platform.codingnomads.co.springtest.lab.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    void deleteAllMovies();

    List<Movie> getAllMoviesWithMinimumRating(Double minRating);
}
