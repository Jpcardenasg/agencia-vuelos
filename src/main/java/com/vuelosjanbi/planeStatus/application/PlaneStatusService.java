package com.vuelosjanbi.planeStatus.application;

import com.vuelosjanbi.planeStatus.application.ports.out.PlaneStatusRepositoryPort;
import com.vuelosjanbi.planeStatus.domain.models.PlaneStatus;
import java.util.Optional;
import java.util.List;

public class PlaneStatusService {

  private PlaneStatusRepositoryPort planeStatusRepositoryPort;

  public PlaneStatusService(PlaneStatusRepositoryPort planeStatusRepositoryPort) {
    this.planeStatusRepositoryPort = planeStatusRepositoryPort;
  }

  public PlaneStatus createPlaneStatus(PlaneStatus planeStatus) {
    return planeStatusRepositoryPort.save(planeStatus);
  }

  public Optional<PlaneStatus> getPlaneStatusById(Long id) {
    return planeStatusRepositoryPort.findById(id);
  }

  public PlaneStatus updatePlaneStatus(PlaneStatus planeStatus) {
    return planeStatusRepositoryPort.save(planeStatus);
  }

  public void deletePlaneStatus(Long id) {
    planeStatusRepositoryPort.deleteById(id);
  }

  public List<PlaneStatus> getAllPlaneStatus() {
    return planeStatusRepositoryPort.findAll();
  }

}
