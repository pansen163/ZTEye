package com.lhzt.monitor.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by pansen on 2017/10/30.
 */
@Service
public class SpringContextUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static Object getBean(String beanName) {
    return applicationContext.getBean(beanName);
  }

  public static Object getBean(Class c) {
    return applicationContext.getBean(c);
  }
}
