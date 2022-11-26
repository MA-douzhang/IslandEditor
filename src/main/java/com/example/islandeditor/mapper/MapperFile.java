package com.example.islandeditor.mapper;

import com.example.islandeditor.entity.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperFile {

  @Insert("INSERT into sys_file(file_Src,name) VALUES (#{fileSrc},#{name})")
  Integer save(Files saveFile);

  @Select("select * from sys_file")
  List<Files> findAll();

//  @Select("SELECT * FROM sys_file  WHERE name LIKE '%.jpg' ")
//  List<Files> findAllImgs();

  @Delete("delete from sys_file where id=#{id}")
  Integer delete(Integer id);

  @Update("update sys_file set name=#{name} where id=#{id}")
  Integer update(Files files);
}
