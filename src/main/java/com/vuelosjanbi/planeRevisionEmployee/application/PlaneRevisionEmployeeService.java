package com.vuelosjanbi.planeRevisionEmployee.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlanRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

@Service
public class PlaneRevisionEmployeeService {

  @Autowired
  private PlaneRevisionEmployeeRepositoryPort planeRevisionEmployeeRepositoryPort;

  public PlaneRevisionEmployeeService(PlaneRevisionEmployeeRepositoryPort planeRevisionEmployeeRepositoryPort) {
    this.planeRevisionEmployeeRepositoryPort = planeRevisionEmployeeRepositoryPort;
  }

  public void createPlaneRevisionEmployee(PlanRevisionEmployee planRevisionEmployee) {
    planeRevisionEmployeeRepositoryPort.save(planRevisionEmployee);
  }

  public PlanRevisionEmployee getPlaneRevisionEmployeeById(RevisionEmployeeId id) {
    return planeRevisionEmployeeRepositoryPort.findById(id).orElse(null);
  }

  public PlanRevisionEmployee updatePlaneRevisionEmployee(PlanRevisionEmployee planRevisionEmployee) {
    return planeRevisionEmployeeRepositoryPort.save(planRevisionEmployee);
  }

  public void deletePlaneRevisionEmployee(RevisionEmployeeId id) {
    planeRevisionEmployeeRepositoryPort.deleteById(id);
  }

  public void getAllPlaneRevisionEmployees() {
    planeRevisionEmployeeRepositoryPort.findAll();
  }
}
