package platform.codingnomads.co.springdata.example.mybatis.resultsandresult;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface MovieMapper {
    // Insert new movie
    @Insert("INSERT INTO mybatis.movies (title, director, rating, length_minutes) VALUES (#{title}, #{director}, #{rating}, #{lengthMinutes});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewMovie(Movie movie);

    // Select by id
    @Select("SELECT * FROM mybatis.movies WHERE id = #{param2};")
    @Results(id = "movieResultMap", value = {
            @Result(property = "title", column = "title"), // We only need to list out the columns that don't match. Just practicing.
            @Result(property = "director", column = "director"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "lengthMinutes", column = "length_minutes")
    })
    Movie getMovieById(Long id);

    // Select by title like
    @Select("SELECT * FROM mybatis.movies WHERE title LIKE '%' #{pattern} '%';")
    @ResultMap("movieResultMap")  // Probably don't need this here since the instance variable name matches the table column name.
    ArrayList<Movie> getMoviesWithTitleLike(String pattern);

    // Update by id
    @Update("UPDATE mybatis.movies SET title = #{title}, director = #{director}, rating = #{rating}, length_minutes = #{lengthMinutes} WHERE id = #{id};")
    void updateMovie(Movie movie);

    // Delete all
    @Delete("TRUNCATE mybatis.movies")
    void deleteAllMovies();
}
