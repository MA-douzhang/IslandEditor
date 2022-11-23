package com.example.islandeditor.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.islandeditor.entity.User;
import com.example.islandeditor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
public class MyInterceptor implements HandlerInterceptor {
  @Autowired
  private UserService userService;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader("token");
    if (StrUtil.isBlank(token)) {
      token = request.getParameter("token");
    }

    // 执行认证
    if (StrUtil.isBlank(token)) {
      throw new Exception(token);
    }
    // 获取 token 中的adminId
    String userId;
    User user;
    try {
      userId = JWT.decode(token).getAudience().get(0);
      // 根据token中的userid查询数据库
      user = userService.getById(Integer.parseInt(userId));
    } catch (Exception e) {
      String errMsg = "token验证失败，请重新登录";
      System.out.println(errMsg + ", token=" + token+e);
      throw new Exception(errMsg);
    }
    if (user == null) {
      throw new Exception("用户不存在");
    }

    try {
      // 用户密码加签验证 token
      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
      jwtVerifier.verify(token); // 验证token
    } catch (JWTVerificationException e) {
      throw new Exception("token验证失败，请重新登录");
    }
    return true;
  }
}
