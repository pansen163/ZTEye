package com.lhzt.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pansen on 2017/10/23.
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {

    return builder.sources(Application.class);
  }


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
