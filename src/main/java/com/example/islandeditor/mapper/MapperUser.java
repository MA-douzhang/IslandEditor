package com.example.islandeditor.mapper;

import com.example.islandeditor.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MapperUser {
  @Select("select * from sys_user")
  List<User> findAll();

  @Insert("INSERT into sys_user(userName,password,phone) values (#{userName},#{password},#{phone})")
  Integer register(User user);
  @Select("select * from sys_user where id=#{id}")
  User getById(Integer id);
  @Select("select * from sys_user where phone=#{phone} and password=#{password}")
  User login(User user);

  @Select("select count(*) from sys_user where phone=#{phone}")
  Integer containPhone(String phone);

  @Update("update sys_user set userName=#{userName},phone=#{phone},password=#{password} where id=#{id}")
  Integer update(User user);
  //新增
  @Insert("INSERT into sys_user(userName,phone,password) VALUES (#{userName},#{phone},#{password})")
  Integer save(User user);
  //删除语句
  @Delete("delete from sys_user where id=#{id}")
  Integer delete(Integer id);


}
