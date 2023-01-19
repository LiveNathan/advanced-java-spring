package platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Album;

import java.util.ArrayList;

@Mapper
public interface AlbumMapper {
    @Insert("INSERT INTO mybatis.albums (name, year) VALUES (#{name}, #{year});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewAlbum(Album album);

    @Select("SELECT * FROM mybatis.albums WHERE id = #{param1};")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "songs",  // Our object
                    column = "id",  // The table column it maps to
                    javaType = ArrayList.class,  // Map to an ArrayList
                    many = @Many(  // Each album has many songs
                            select = "platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.SongMapper.getSongsByAlbumId",  // Method to use
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Album getAlbumByIdWithSongs(Long id);

    @Select("SELECT * FROM mybatis.albums WHERE id = #{param1};")
    Album getAlbumByIdWithoutSongs(Long id);
}
