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
public class Lesson {
    private Long id;
    private String name;
    private String text;
    private List<Image> imageArrayList;
}
