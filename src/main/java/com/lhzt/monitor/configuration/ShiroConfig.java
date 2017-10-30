package com.lhzt.monitor.configuration;

import com.lhzt.monitor.SpringCacheManagerWrapper;
import com.lhzt.monitor.credentials.RetryLimitHashedCredentialsMatcher;
import com.lhzt.monitor.filter.SysUserFilter;
import com.lhzt.monitor.realm.UserRealm;
import com.lhzt.monitor.service.UserService;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

/**
 * Created by pansen on 2017/10/30.
 */
@Configuration
@Import({CacheConfig.class, SpringContextUtil.class})
public class ShiroConfig {

  //Shiro的Web过滤器
  @Bean
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // 必须设置 SecurityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 未授权界面;
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");

    //过滤器
    Map<String, Filter> filters = new HashMap<>();
    filters.put("authc", getFormAuthenticationFilter());
    filters.put("sysUser", getSysUserFilter());

    // 拦截器.
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    // 配置不会被拦截的链接 顺序判断
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/logout", "logout");
    filterChainDefinitionMap.put("/authenticated", "authc");
    filterChainDefinitionMap.put("/**", "user,sysUser");

    shiroFilterFactoryBean.setFilters(filters);
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    System.out.println("Shiro拦截器工厂类注入成功");
    return shiroFilterFactoryBean;
  }

  //Realm实现
  @Bean
  public UserRealm getUserRealm(CredentialsMatcher matcher) {
    UserService userService = new UserService();
    UserRealm myShiroRealm = new UserRealm();
    myShiroRealm.setUserService(userService);
    myShiroRealm.setCredentialsMatcher(matcher);
    myShiroRealm.setCachingEnabled(false);
    return myShiroRealm;
  }

  //Shiro生命周期处理器
  @Bean
  public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  //缓存管理器
  @Bean
  public SpringCacheManagerWrapper getSpringCacheManagerWrapper(CacheManager cacheManager) {
    SpringCacheManagerWrapper cacheManagerWrapper = new SpringCacheManagerWrapper();
    cacheManagerWrapper.setCacheManager(cacheManager);
    return cacheManagerWrapper;
  }


  //凭证匹配器
  @Bean
  public CredentialsMatcher getRetryLimitHashedCredentialsMatcher(
      SpringCacheManagerWrapper managerWrapper) {
    RetryLimitHashedCredentialsMatcher
        matcher =
        new RetryLimitHashedCredentialsMatcher(managerWrapper);
    matcher.setHashAlgorithmName("md5");
    matcher.setHashIterations(2);
    matcher.setStoredCredentialsHexEncoded(true);
    return matcher;
  }

  //会话ID生成器
  @Bean
  public JavaUuidSessionIdGenerator getJavaUuidSessionIdGenerator() {
    return new JavaUuidSessionIdGenerator();
  }

  //会话Cookie模板
  @Bean
  public SimpleCookie getSimpleCookie() {
    SimpleCookie simpleCookie = new SimpleCookie("sid");
    simpleCookie.setHttpOnly(true);
    simpleCookie.setMaxAge(-1);
    return simpleCookie;
  }

  //会话DAO
  @Bean
  public EnterpriseCacheSessionDAO getEnterpriseCacheSessionDAO() {
    EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
    dao.setActiveSessionsCacheName("shiro-activeSessionCache");
    dao.setSessionIdGenerator(getJavaUuidSessionIdGenerator());
    return dao;
  }

  //会话验证调度器
  @Bean
  public QuartzSessionValidationScheduler getQuartzSessionValidationScheduler() {
    QuartzSessionValidationScheduler
        sessionValidationScheduler =
        new QuartzSessionValidationScheduler();
    sessionValidationScheduler.setSessionValidationInterval(1800000);
    DefaultWebSessionManager manager = getDefaultWebSessionManager(sessionValidationScheduler);
    sessionValidationScheduler.setSessionManager(manager);
    return sessionValidationScheduler;
  }

  //会话管理器
  @Bean
  public DefaultWebSessionManager getDefaultWebSessionManager(
      QuartzSessionValidationScheduler sessionValidationScheduler) {
    DefaultWebSessionManager manager = new DefaultWebSessionManager();
    manager.setGlobalSessionTimeout(1800000);
    manager.setDeleteInvalidSessions(true);
    manager.setSessionValidationSchedulerEnabled(true);
    manager.setSessionValidationScheduler(sessionValidationScheduler);
    manager.setSessionDAO(getEnterpriseCacheSessionDAO());
    manager.setSessionIdCookieEnabled(true);
    manager.setSessionIdCookie(getSimpleCookie());
    return manager;
  }

  //安全管理器
  @Bean
  public SecurityManager getDefaultWebSecurityManager(
      SpringCacheManagerWrapper springCacheManagerWrapper, UserRealm userRealm,
      DefaultWebSessionManager defaultWebSessionManager) {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(userRealm);
    manager.setSessionManager(defaultWebSessionManager);
    manager.setCacheManager(springCacheManagerWrapper);
    return manager;
  }

  //相当于调用SecurityUtils.setSecurityManager(securityManager)
  @Bean
  public MethodInvokingFactoryBean getMethodInvokingFactoryBean(SecurityManager securityManager) {
    MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
    factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
    factoryBean.setArguments(new Object[]{securityManager});
    return factoryBean;
  }

  //基于Form表单的身份验证过滤器
  @Bean
  public FormAuthenticationFilter getFormAuthenticationFilter() {
    FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
    formAuthenticationFilter.setUsernameParam("username");
    formAuthenticationFilter.setPasswordParam("password");
    formAuthenticationFilter.setLoginUrl("/login");
    return formAuthenticationFilter;
  }

  @Bean
  public SysUserFilter getSysUserFilter() {
    UserService userService = new UserService();
    SysUserFilter sysUserFilter = new SysUserFilter();
    sysUserFilter.setUserService(userService);
    return sysUserFilter;
  }

  /**
   * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

}
