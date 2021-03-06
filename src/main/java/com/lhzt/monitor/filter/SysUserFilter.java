package com.lhzt.monitor.filter;


import com.lhzt.monitor.constants.Constants;
import com.lhzt.monitor.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: Zhang Kaitao <p>Date: 14-2-15 <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

  private UserService userService;

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected boolean onPreHandle(ServletRequest request, ServletResponse response,
                                Object mappedValue) throws Exception {

    String username = (String) SecurityUtils.getSubject().getPrincipal();
    request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
    return true;
  }
}
