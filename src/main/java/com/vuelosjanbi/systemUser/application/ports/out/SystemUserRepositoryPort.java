package com.vuelosjanbi.systemUser.application.ports.out;

import java.util.Optional;

import com.vuelosjanbi.systemUser.domain.SystemUser;

public interface SystemUserRepositoryPort {

  SystemUser save(SystemUser systemUser);

  Optional<SystemUser> findById(Long id);

  Optional<SystemUser> findByUsername(String username);

  void deleteById(Long id);

  void deleteByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsById(Long id);

  boolean existsByUsernameAndPassword(String username, String password);

  Optional<SystemUser> findByUsernameAndPassword(String username, String password);

}
