package com.zxh.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;


@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
public class BookApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookApplication.class, args);
  }

}
