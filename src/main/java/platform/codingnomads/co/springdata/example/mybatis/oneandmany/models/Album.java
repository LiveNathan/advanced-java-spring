package platform.codingnomads.co.springdata.example.mybatis.oneandmany.models;

import lombok.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"songs", "artist"})  // Song handles these toStrings
public class Album {
    private String name;
    private int year;
    private Artist artist;
    private List<Song> songs;
}
