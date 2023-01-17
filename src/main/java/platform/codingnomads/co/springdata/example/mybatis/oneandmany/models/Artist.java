package platform.codingnomads.co.springdata.example.mybatis.oneandmany.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "albums")
@Builder
public class Artist {

    private Long id;

    private String name;

    private String bio;

    private List<Album> albums;
}
