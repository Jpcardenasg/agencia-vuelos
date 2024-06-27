package com.vuelosjanbi.systemUser.application.ports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.systemUser.application.ports.out.SystemUserRepositoryPort;
import com.vuelosjanbi.systemUser.domain.SystemUser;

@Service
public class SystemUserService {

  @Autowired
  private SystemUserRepositoryPort systemUserRepositoryPort;

  public SystemUserService(SystemUserRepositoryPort systemUserRepositoryPort) {
    this.systemUserRepositoryPort = systemUserRepositoryPort;
  }

  public SystemUserService() {
  }

  public SystemUser createSystemUser(SystemUser systemUser) {
    return systemUserRepositoryPort.save(systemUser);
  }

  public SystemUser getSystemUserById(Long id) {
    return systemUserRepositoryPort.findById(id).orElse(null);
  }

  public SystemUser getSystemUserByUsername(String username) {
    return systemUserRepositoryPort.findByUsername(username).orElse(null);
  }

  public void deleteSystemUserById(Long id) {
    systemUserRepositoryPort.deleteById(id);
  }

  public void deleteSystemUserByUsername(String username) {
    systemUserRepositoryPort.deleteByUsername(username);
  }

  public boolean existsSystemUserByUsername(String username) {
    return systemUserRepositoryPort.existsByUsername(username);
  }

  public boolean existsSystemUserById(Long id) {
    return systemUserRepositoryPort.existsById(id);
  }

  public boolean existsSystemUserByUsernameAndPassword(String username, String password) {
    return systemUserRepositoryPort.existsByUsernameAndPassword(username, password);
  }

  public SystemUser getSystemUserByUsernameAndPassword(String username, String password) {
    return systemUserRepositoryPort.findByUsernameAndPassword(username, password).orElse(null);
  }
}
