package com.zxh.blog;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
@Slf4j
public class BookApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
    System.out.println(SaManager.getConfig());
    log.debug("test");
  }

}
