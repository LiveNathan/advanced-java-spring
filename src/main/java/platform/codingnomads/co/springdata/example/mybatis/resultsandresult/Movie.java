package platform.codingnomads.co.springdata.example.mybatis.resultsandresult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
private Long id;
private String title;
private String director;
private String rating;
private int lengthMinutes;
}
