package com.zxh.blog.aspect;

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
      SaRouter.match("/**", "/user/doLogin", r -> StpUtil.checkLogin());
      SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));

      SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
      SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
      SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
      SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
      SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
      SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));

      // 甚至你可以随意的写一个打印语句
      SaRouter.match("/**", r -> System.out.println("----啦啦啦----"));
      // 连缀写法
      SaRouter.match("/**").check(r -> System.out.println("----啦啦啦----"));

    })).addPathPatterns("/**");
  }
}