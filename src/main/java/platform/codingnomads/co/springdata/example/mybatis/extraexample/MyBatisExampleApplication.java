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
        // Clear tables  // Could not make this work because of cascade restrictions.
//        sectionMapper.deleteAll();
//        chapterMapper.deleteAll();
//        lessonMapper.deleteAll();
//        imageMapper.deleteAll();
//        imageMapper.deleteImageByName("crossover");

        // Section
        sectionMapper.insertNewSection("Section1");
        Long sectionId = sectionMapper.getSectionIdByName("Section1");

        Section section = Section.builder().name("Section2").build();
        System.out.println("before save: " + section);
        sectionMapper.insertNewSectionByObject(section);
        System.out.println("after save: " + section);

        // Chapter
        chapterMapper.insertNewChapter("Chapter3", sectionId);
        Long chapterId = chapterMapper.getChapterIdByName("Chapter3");

        // Lesson
        lessonMapper.insertNewLesson("Lesson2", "This be Lesson 2.", chapterId);
        Long lessonId = lessonMapper.getLessonIdByName("Lesson2");

        // Image
        byte[] array = Files.readAllBytes(Paths.get("/Users/nathanlively/Downloads/stencil.instagram-photo (4).jpg"));
        imageMapper.insertNewImage("Image2", array);

        // Connect image to lesson
        lessonMapper.addImageToLesson(lessonId, "Image2");




        /* Create all objects first */  // Could not figure out how to make this section work!
        // Image
//        byte[] array = Files.readAllBytes(Paths.get("/Users/nathanlively/Downloads/stencil.instagram-photo (4).jpg"));
        Image image = Image.builder().name("Image1").imageData(array).build();
        List<Image> images = List.of(image);

        // Lesson
        Lesson lesson = Lesson.builder().name("Lesson1").text("This is lesson 1.").imageArrayList(images).build();
        List<Lesson> lessons = List.of(lesson);

        // Chapter
        Chapter chapter = Chapter.builder().name("Chapter1").lessons(lessons).build();
        List<Chapter> chapters = List.of(chapter);

        // Section
        Section section3 = Section.builder().name("Section3").chapters(chapters).build();

        /* Then insert them */
        sectionMapper.insertNewSectionByObject(section3);
//        chapterMapper.insertNewChapterByObject(chapter, section3.getId());// Chapter
//        imageMapper.insertNewImageByObject(image);  // Image
//        lessonMapper.insertNewLessonByObject(lesson);  // Lesson
    }
}
