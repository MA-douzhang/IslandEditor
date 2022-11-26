package com.example.islandeditor.service.impl;

import com.example.islandeditor.entity.User;

import java.util.List;

public interface UserServiceImpl {
  User login(User user);
  Integer register(User user);

  User getById(Integer id);
  List<User> findAll();
  Integer containPhone(String password);
  Integer save(User user);
  //新闻删除方法
  Integer delete(Integer id);

}
