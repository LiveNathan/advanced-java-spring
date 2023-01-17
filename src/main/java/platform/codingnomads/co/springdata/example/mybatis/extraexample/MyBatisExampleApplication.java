package platform.codingnomads.co.springdata.example.mybatis.extraexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.ChapterMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.ImageMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.LessonMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.SectionMapper;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Chapter;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Image;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Lesson;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Section;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class MyBatisExampleApplication implements CommandLineRunner {

    /* Before running this app, be sure to:

        * create a new empty schema in the mysql database named "mybatis"

        * execute the SQL found "database_structure.sql" on the mybatis schema

        * update the "spring.datasource.url" property in your application.properties file to
          jdbc:mysql://localhost:3306/mybatis?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

     */

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    LessonMapper lessonMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    SectionMapper sectionMapper;

    public static void main(String[] args) {
        SpringApplication.run(MyBatisExampleApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear tables
//        sectionMapper.deleteAll();
//        chapterMapper.deleteAll();
//        lessonMapper.deleteAll();
//        imageMapper.deleteAll();
        imageMapper.deleteImageByName("crossover");

        // Image
        byte[] array = Files.readAllBytes(Paths.get("/Users/nathanlively/Downloads/stencil.instagram-photo (4).jpg"));
        Image image = Image.builder().name("image1").imageData(array).build();
        List<Image> images = List.of(image);
//        imageMapper.insertNewImage("crossover", array);

        // Lesson
        Lesson lesson = Lesson.builder().name("lesson1").text("This is lesson 1.").imageArrayList(images).build();
        List<Lesson> lessons = List.of(lesson);
//        lessonMapper.insertNewLesson();

        // Chapter
        Chapter chapter = Chapter.builder().name("Alpha").lessons(lessons).build();
        List<Chapter> chapters = List.of(chapter);
//        chapterMapper.insertNewChapter("alpha",);

        // Section
        Section section = Section.builder().name("A").chapters(chapters).build();
//        sectionMapper.insertNewSection("A");











    }
}
