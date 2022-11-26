package com.example.islandeditor.service;

import com.example.islandeditor.common.TokenUtils;
import com.example.islandeditor.entity.User;
import com.example.islandeditor.mapper.MapperUser;
import com.example.islandeditor.service.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements UserServiceImpl {
  @Resource
  private MapperUser mapperUser;
  @Override
  public User login(User user) {
    User user1 = mapperUser.login(user);
    if(user1 != null){
      String token = TokenUtils.genToken("" + user1.getId(), user1.getPassword());
      user1.setToken(token);
      return user1;
    }
    //查询未通过
    return null;
  }


  @Override
  public Integer register(User user) {
    //查询号码是否被注册
    Integer containPhone = mapperUser.containPhone(user.getPhone());
    //0为未注册 1为已经注册
    if(containPhone == 0) {
      mapperUser.register(user);
      return 0;
    }
    return 1;
  }

  @Override
  public User getById(Integer id) {
    return mapperUser.getById(id);
  }

  @Override
  public List<User> findAll() {
    return mapperUser.findAll();
  }

  @Override
  public Integer containPhone(String phone) {
    return mapperUser.containPhone(phone);
  }

  @Override
  public Integer save(User user) {
    //Id 为null时新增用户
    if(user.getId()==null){
      return mapperUser.save(user);
    }else {
      // 修改用户信息
      return mapperUser.update(user);
    }
  }

  @Override
  public Integer delete(Integer id) {
    return mapperUser.delete(id);
  }
}
