package platform.codingnomads.co.springdata.example.mybatis.oneandmany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.AlbumMapper;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.ArtistMapper;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.mappers.SongMapper;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Album;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Artist;
import platform.codingnomads.co.springdata.example.mybatis.oneandmany.models.Song;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OneAndManyApplication {

    /* Before running this app, be sure to:

        * create a new empty schema in the mysql database named "mybatis"

        * execute the SQL found "mybatis_tables.sql" on the mybatis schema

        * update the "spring.datasource.url" property in your application.properties file to
          jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

     */

    public static void main(String[] args) {
        SpringApplication.run(OneAndManyApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper, ArtistMapper artistMapper, AlbumMapper albumMapper) {
        return (args) -> {

            // Create artist
            Artist artist1 = Artist.builder().name("Bon Iver").bio("Bon Iver is an American indie folk band").build();

            // Create albums
            Album album1 = Album.builder().name("Bon Iver").year(2011).artist(artist1).build();
            Album album2 = Album.builder().name("For Emma, Forever Ago").year(2007).artist(artist1).build();

            // Create song
            Song song1 = Song.builder().name("Minnesota, WI").songLength(232).album(album1).artist(artist1).build();
            Song song2 = Song.builder().name("Calgary").songLength(250).album(album1).artist(artist1).build();
            Song song3 = Song.builder().name("Flume").songLength(219).album(album2).artist(artist1).build();
            Song song4 = Song.builder().name("Re: Stacks").songLength(401).album(album2).artist(artist1).build();
            List<Song> songs = List.of(song1, song2, song3, song4);

            // Connect artist to album
            ArrayList<Album> albums1 = new ArrayList<>();
            albums1.add(album1);
            albums1.add(album2);
            artist1.setAlbums(albums1);

            // Connect album to songs. Do we really need to do this if we have connected them above??
            album1.setSongs(List.of(song1, song2));
            album2.setSongs(List.of(song3, song4));

            // Insert
            artistMapper.insertNewArtist(artist1);
            albumMapper.insertNewAlbum(album1);
            albumMapper.insertNewAlbum(album2);
            for (Song song :
                    songs) {
                songMapper.insertNewSong(song);
            }

            // getSongById
            System.out.println("\n** getSongById **");
            Song songById = songMapper.getSongById(1L);
            System.out.println(songById.toString());

            // getArtistByIdWithSongsAndAlbums
            System.out.println("\n** getArtistByIdWithSongsAndAlbums **");
            Artist artist3 = artistMapper.getArtistByIdWithSongsAndAlbums(1L);
            System.out.println(artist3.toString());
            List<Album> albums = artist3.getAlbums();
            System.out.println("Their albums: ");
            albums.forEach(System.out::println);
            List<Song> songs1 = albums.get(0).getSongs();
            System.out.println("The first album's songs: ");
            songs1.forEach(System.out::println);
        };
    }
}
