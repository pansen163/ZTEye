package com.lhzt.monitor.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pansen on 2017/10/30.
 */
@Controller
public class UserController {

  Logger logger= LoggerFactory.getLogger(UserController.class);

  @RequestMapping("/user/creat")
  @RequiresPermissions("user:create")
  public String creatUser() {
    logger.info("creat success");
    return "success";
  }

  @RequestMapping("/user/update")
  @RequiresPermissions("user:update")
  public String updateUser() {
    logger.info("creat success");
    return "success";
  }
}
