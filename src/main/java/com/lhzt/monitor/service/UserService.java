package com.lhzt.monitor.service;

import com.lhzt.monitor.model.User;

import java.util.Set;

/**
 * Created by pansen on 2017/10/30.
 */
public interface UserService {

  User findByUsername(String userName);

  Set<String> findRoles(String userName);

  Set<String> findPermissions(String userName);

}
