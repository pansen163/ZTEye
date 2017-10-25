package com.lhzt.monitor.controller;



import com.lhzt.monitor.mapper.ContractMapper;
import com.lhzt.monitor.model.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pansen on 2017/10/23.
 */
@Controller
public class Test {

  @Autowired
  ContractMapper contractMapper;

  @ResponseBody
  @RequestMapping("contract")
  public Contract hello() {
    Contract contract = contractMapper.selectByPrimaryKey("0000000000000000001");
    return contract;
  }

}
