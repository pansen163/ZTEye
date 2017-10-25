package com.lhzt.monitor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by pansen on 2017/10/25.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
  @Bean
  public InternalResourceViewResolver viewResolver(){
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/pages/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setViewClass(JstlView.class);
    return  viewResolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //super.addResourceHandlers(registry);
    //addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
  }
}
