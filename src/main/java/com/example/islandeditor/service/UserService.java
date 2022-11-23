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
    if(user.getUserName().equals(user1.getUserName())&&user.getPassword().equals(user1.getPassword())){
      String token = TokenUtils.genToken("" + user1.getId(), user1.getPassword());
      user1.setToken(token);
      return user1;
    }
    //查询未通过
    return null;
  }


  @Override
  public Integer register(User user) {
    String name = user.getUserName();
    String password = user.getPassword();
    //判断账号和密码是否格式正确
    if(name.equals("")||password.equals("")){
      return 0;
    }
    return mapperUser.register(user);
  }

  @Override
  public User getById(Integer id) {
    return mapperUser.getById(id);
  }

  @Override
  public List<User> findAll() {
    return mapperUser.findAll();
  }
}
