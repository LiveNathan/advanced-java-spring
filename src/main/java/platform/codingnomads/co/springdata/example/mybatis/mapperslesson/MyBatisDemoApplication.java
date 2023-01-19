package platform.codingnomads.co.springdata.example.mybatis.mapperslesson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MyBatisDemoApplication {

    /* Before running this app, be sure to:

        * create a new empty schema in the mysql database named "mybatis"

        * execute the SQL found "songs_table.sql" on the mybatis schema

        * update the "spring.datasource.url" property in your application.properties file to
          jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

     */

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(SongMapper songMapper) {
        return (args) -> {
            Song song1 = new Song();
            song1.setName("Minnesota, WI");
            song1.setAlbum_name("Bon Iver");
            song1.setArtist_name("Bon Iver");
            song1.setSong_length(232);

            Song song2 = new Song();
            song2.setName("Post Humorous");
            song2.setAlbum_name("Orca");
            song2.setArtist_name("Gus Dapperton");
            song2.setSong_length(279);

            Song songBitches = Song.builder().name("Bitches").album_name("Quality Over Opinion").artist_name("Louis Cole").song_length(259).build();
            Song songBloodsport = Song.builder().name("Bloodsport '15").album_name("You're a Man Now, Boy").artist_name("Raleigh Ritchie").song_length(257).build();
            Song songGe = Song.builder().name("Ge").album_name("Heavy Easy Listening").artist_name("Bolinas").song_length(204).build();
            List<Song> songs = List.of(song1, song2, songBitches, songBloodsport, songGe);
            for (Song song : songs) {
                songMapper.insertNewSong(song);
            }

            // getSongById
            System.out.println("\n** getSongById **");
            Song song3 = songMapper.getSongById(1L);
            System.out.println(song3.toString());

            // getSongsByName
            System.out.println("\n** getSongsByName **");
            ArrayList<Song> songs1 = songMapper.getSongsByName("Ge");
            songs1.forEach(System.out::println);

            // getSongsWithNameLike
            System.out.println("\n** getSongsWithNameLike **");
            ArrayList<Song> songsLike = songMapper.getSongsWithNameLike("sport");
            songsLike.forEach(System.out::println);

            // getSongsWithLengthGreaterThan
            System.out.println("\n** getSongsWithLengthGreaterThan **");
            ArrayList<Song> longSongs = songMapper.getSongsWithLengthGreaterThan(250);
            longSongs.forEach(System.out::println);

            // getSongsByAlbumAndArtist
            System.out.println("\n** getSongsByAlbumAndArtist **");
            ArrayList<Song> songsByAlbumAndArtist = songMapper.getSongsByAlbumAndArtist("Bon Iver", "Bon Iver");
            songsByAlbumAndArtist.forEach(System.out::println);

            // getSongsByArtist
            System.out.println("\n** getSongsByArtist **");
            ArrayList<Song> songsByArtist = songMapper.getSongsByArtist("Louis Cole");
            songsByArtist.forEach(System.out::println);

            // updateSong
            System.out.println("\n** updateSong **");
            songBitches.setSong_length(260);  // Change song length
            songMapper.updateSong(songBitches);  // Update

            // deleteSongById
            System.out.println("\n** deleteSongById **");
            songMapper.deleteSongById(2L);

            // deleteSongsByAlbumAndArtist
            System.out.println("\n** deleteSongsByAlbumAndArtist **");
            songMapper.deleteSongsByAlbumAndArtist("Bon Iver", "Bon Iver");

            // Delete all
            songMapper.deleteAllSongs();
        };
    }
}
