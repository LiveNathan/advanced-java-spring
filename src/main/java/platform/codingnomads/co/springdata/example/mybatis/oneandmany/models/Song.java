package platform.codingnomads.co.springdata.example.mybatis.oneandmany.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    private Long id;

    private String name;

    private Album album;

    private Artist artist;

    //song length in seconds
    private int songLength;
}
