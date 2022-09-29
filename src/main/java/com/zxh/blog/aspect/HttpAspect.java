package com.zxh.blog.aspect;


import cn.dev33.satoken.util.SaResult;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class HttpAspect {

  @Pointcut("execution(public * com.zxh.blog.Controller.*.*(..))")
  public void log() {
  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (Objects.isNull(attributes)) {
      return;
    }
    HttpServletRequest request = attributes.getRequest();
    //url method ip
    log.info("接收到请求，url={}，method={}，ip={}", request.getRequestURL(), request.getMethod(), request.getRemoteAddr());

    //类方法 参数
    log.info("class_method={}, args={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
  }

  @After("log()")
  public void doAfter() {
    log.info("请求完成");
  }

  @AfterReturning(returning = "object", pointcut = "log()")
  public void doAfterReturning(Object object) {
    if (object != null && object instanceof SaResult) {
      SaResult res = (SaResult) object;
      if (res.getCode() != 0) {
        log.debug("response={}", object != null ? object.toString() : null);
      }
    }
  }

}
