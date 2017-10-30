package com.lhzt.monitor.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by pansen on 2017/10/30.
 */
@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public EhCacheManagerFactoryBean getEhCacheManagerFactoryBean() {
    System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
    EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
    cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
    cacheManagerFactoryBean.setShared(true);
    return cacheManagerFactoryBean;
  }

  @Bean
  public CacheManager getEhCacheCacheManager() {
    EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
    ehCacheCacheManager.setCacheManager(getEhCacheManagerFactoryBean().getObject());
    return ehCacheCacheManager;
  }


}
