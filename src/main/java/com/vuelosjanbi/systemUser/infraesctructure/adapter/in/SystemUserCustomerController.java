package com.vuelosjanbi.systemUser.infraesctructure.adapter.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.systemUser.application.ports.SystemUserService;

@Controller
public class SystemUserCustomerController {

  @Autowired
  private SystemUserService systemUserService;

}
