package platform.codingnomads.co.springdata.example.mybatis.extraexample.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    private String name;
    private byte[] imageData;
}
