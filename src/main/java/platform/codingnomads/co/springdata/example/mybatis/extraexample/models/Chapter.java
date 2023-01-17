package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chapter {
    private Long id;
    private String name;
    private List<Lesson> lessons;
}
