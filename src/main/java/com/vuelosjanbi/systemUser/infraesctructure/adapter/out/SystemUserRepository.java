package com.vuelosjanbi.systemUser.infraesctructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.systemUser.application.ports.out.SystemUserRepositoryPort;
import com.vuelosjanbi.systemUser.domain.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long>, SystemUserRepositoryPort {

}
