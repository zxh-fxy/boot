package com.zxh.blog.security;

import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//FilterSecurityInterceptor 基本最底层
//ExceptionTranslationFilter 处理认证授权过程中的有异常
//UsernamePasswordAuthenticationFilter login的post请求拦截
public class FilterSecurityInterceptortest extends FilterSecurityInterceptor{

  public static void main(String[] args) {
    UsernamePasswordAuthenticationFilter
  }
}
