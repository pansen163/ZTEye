package com.lhzt.monitor.controller;


import com.lhzt.monitor.annotation.CurrentUser;
import com.lhzt.monitor.model.User;
import com.lhzt.monitor.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {


    Logger logger= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        logger.info("permissions:{}",permissions);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
