package com.example.demo.mapper;
import com.example.demo.model.LOGIN;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Mapper {
    @Select("SELECT * FROM POST")
    List<LOGIN> getAll();

    @Select("SELECT * from POST WHERE id = #{id}")
    LOGIN getPost(@Param("id") int id);

    @Insert("INSERT INTO POST(title, body, createdAt, updatedAt) VALUES(#{title}, #{body}, now(), now())")
    void insertData(@Param("title") String title, @Param("body") String body);

    @Delete("DELETE FROM POST WHERE id=#{id}")
    void deleteData(@Param("id") int id);

    @Update("UPDATE POST SET title =#{title},body=#{body} WHERE id=#{id}")
    void updateData(@Param("title") String title, @Param("body")String body, @Param("id") int id);
}
