package com.vuelosjanbi.planeRevisionEmployee.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.planeRevisionEmployee.application.ports.out.PlaneRevisionEmployeeRepositoryPort;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.PlaneRevisionEmployee;
import com.vuelosjanbi.planeRevisionEmployee.domain.models.RevisionEmployeeId;

@Service
public class PlaneRevisionEmployeeService {

  @Autowired
  private PlaneRevisionEmployeeRepositoryPort planeRevisionEmployeeRepositoryPort;

  public PlaneRevisionEmployeeService(PlaneRevisionEmployeeRepositoryPort planeRevisionEmployeeRepositoryPort) {
    this.planeRevisionEmployeeRepositoryPort = planeRevisionEmployeeRepositoryPort;
  }

  public void createPlaneRevisionEmployee(PlaneRevisionEmployee planRevisionEmployee) {
    planeRevisionEmployeeRepositoryPort.save(planRevisionEmployee);
  }

  public PlaneRevisionEmployee getPlaneRevisionEmployeeById(RevisionEmployeeId id) {
    return planeRevisionEmployeeRepositoryPort.findById(id).orElse(null);
  }

  public PlaneRevisionEmployee updatePlaneRevisionEmployee(PlaneRevisionEmployee planRevisionEmployee) {
    return planeRevisionEmployeeRepositoryPort.save(planRevisionEmployee);
  }

  public void deletePlaneRevisionEmployee(RevisionEmployeeId id) {
    planeRevisionEmployeeRepositoryPort.deleteById(id);
  }

  public void getAllPlaneRevisionEmployees() {
    planeRevisionEmployeeRepositoryPort.findAll();
  }
}
