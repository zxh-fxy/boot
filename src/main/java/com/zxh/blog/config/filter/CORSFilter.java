package com.zxh.blog.config.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String origin = request.getHeader("Origin");
    //后续可加入白名单，同步application   TODO
    response.setHeader("Access-Control-Allow-Origin",origin);
    response.setHeader("Access-Control-Allow-Credentials","true");
    response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE,PATCH,HEAD");
    response.setHeader("Access-Control-Allow-Max-Age","3600");
    response.setHeader("Access-Control-Allow-Headers","*");
    if("OPTIONS".equalsIgnoreCase(request.getMethod())){
      response.setStatus(HttpServletResponse.SC_OK);
    }else{
      filterChain.doFilter(servletRequest,servletResponse);
    }
  }

  @Override
  public void destroy() {

  }
}