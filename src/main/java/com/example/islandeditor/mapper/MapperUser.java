package com.example.islandeditor.mapper;

import com.example.islandeditor.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MapperUser {
  @Select("select * from sys_user")
  List<User> findAll();

  @Insert("INSERT into sys_user(userName,password) values (#{userName},#{password})")
  Integer register(User user);
  @Select("select * from sys_user where id=#{id}")
  User getById(Integer id);
  @Select("select * from sys_user where userName=#{userName} and password=#{password}")
  User login(User user);
}
