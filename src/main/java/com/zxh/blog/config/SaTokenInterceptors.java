package com.zxh.blog.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenInterceptors implements WebMvcConfigurer {
  // 注册拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
    registry.addInterceptor(new SaInterceptor(handler -> {
      // 根据路由划分模块，不同模块不同鉴权
      SaRouter.match("/**", "/userlogin/*", r -> StpUtil.checkLogin());
      SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin"));
    })).addPathPatterns("/**");
  }
}