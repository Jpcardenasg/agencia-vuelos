package com.vuelosjanbi.crewRole.infrastructure.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.crewRole.domain.models.CrewRole;

@Repository
public interface CrewRoleRepository extends JpaRepository<CrewRole, Long> {

}
