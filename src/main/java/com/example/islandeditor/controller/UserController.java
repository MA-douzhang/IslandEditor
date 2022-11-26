package com.example.islandeditor.controller;

import com.example.islandeditor.entity.User;
import com.example.islandeditor.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  UserService userService;
  @GetMapping("/")
  public List<User> getAll(){
    return userService.findAll();
  }

  @PostMapping("/register")
  public Integer register(@RequestBody User user){
    return userService.register(user);
  }
  //@RequestBody 只接受JSON封装数据 不加接收key-value键值对数据
  @PostMapping("/login")
  public User login(@RequestBody User user){
    return userService.login(user);
  }
  //用户新增 或者用户信息更新
  @PostMapping("/save")
  public Integer save(@RequestBody User user){
    return userService.save(user);
  }
  //删除
  @DeleteMapping({"/{id}"})
  public Integer delete(@PathVariable Integer id){
    return userService.delete(id);
  }
}
