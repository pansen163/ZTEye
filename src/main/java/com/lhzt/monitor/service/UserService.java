package com.lhzt.monitor.service;

import com.lhzt.monitor.model.User;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pansen on 2017/10/30.
 */
@Service
public class UserService {

  public User findByUsername(String userName) {
    User user = new User();
    user.setUsername("admin");
    user.setPassword("d1fba7cf319eff5f0aa6facc565dde76");
    user.setSalt("05b2a29a0acd28470aa0f9ec06a62bc5");
    return user;
  }

  public Set<String> findRoles(String userName) {
    Set<String> roles = new HashSet<>();
    roles.add("test");
    return roles;
  }

  public Set<String> findPermissions(String userName) {
    Set<String> permissions = new HashSet<>();
    permissions.add("user:create");
    return permissions;
  }
}
