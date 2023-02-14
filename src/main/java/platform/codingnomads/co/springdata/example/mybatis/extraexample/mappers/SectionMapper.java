package platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import platform.codingnomads.co.springdata.example.mybatis.extraexample.models.Section;

import java.util.List;

@Mapper
public interface SectionMapper {

    @Insert("INSERT INTO mybatis.sections (name) VALUES (#{name});")
    @Options(useGeneratedKeys = true, keyColumn = "id")  // Removing keyProperty fixed this method because it was trying return the id.
    void insertNewSection(String name);  // This omits the id field! Java will have to create the object.

    @Insert("INSERT INTO mybatis.sections (name) VALUES (#{name});")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertNewSectionByObject(Section section);  // I provide the object. On the way back, update my object with the id.

    @Select("SELECT id FROM mybatis.sections WHERE name = #{name};")
    Long getSectionIdByName(String name);

    @Select("SELECT id, name FROM mybatis.sections WHERE id = #{param1};")
    @Results(
            @Result(
                    property = "chapters",
                    column = "id",
                    javaType = List.class,
                    many = @Many(
                            select = "platform.codingnomads.co.springdata.example.mybatis.extraexample.mappers.ChapterMapper.getChaptersBySectionId",
                            fetchType = FetchType.LAZY
                    )
            )
    )
    Section getSectionById(Long sectionId);

    @Delete("DELETE FROM mybatis.sections WHERE id = #{id};")
    int deleteSectionById(Long id);  // Returns number of rows affected.

    @Delete("TRUNCATE mybatis.sections")
    void deleteAll();
}
