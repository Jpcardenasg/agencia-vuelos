package com.vuelosjanbi.crewRole.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.crewRole.application.ports.out.CrewRoleRepositoryport;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

@Service
public class CrewRoleService {

    @Autowired
    private CrewRoleRepositoryport crewRoleRepositoryport;

    public CrewRoleService(CrewRoleRepositoryport crewRoleRepositoryport) {
        this.crewRoleRepositoryport = crewRoleRepositoryport;
    }

    public CrewRole createCrewRole(CrewRole crewRole) {
        return crewRoleRepositoryport.save(crewRole);
    }

    public CrewRole updateCrewRole(CrewRole crewRole) {
        return crewRoleRepositoryport.save(crewRole);
    }

    public void deleteCrewRole(Long crewRoleId) {
        crewRoleRepositoryport.deleteById(crewRoleId);
    }

    public Optional<CrewRole> getCrewRoleById(Long crewRoleId) {
        return crewRoleRepositoryport.findById(crewRoleId);
    }

    public Optional<CrewRole> getCrewRoleByName(String crewRoleName) {
        return crewRoleRepositoryport.findByName(crewRoleName);
    }

    public List<CrewRole> getAllCrewRoles() {
        return crewRoleRepositoryport.findAll();
    }

}
