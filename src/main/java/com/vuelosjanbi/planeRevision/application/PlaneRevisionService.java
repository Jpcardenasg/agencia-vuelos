package com.vuelosjanbi.planeRevision.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.planeRevision.application.ports.out.PlaneRevisionRepositoryPort;
import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;

@Service
public class PlaneRevisionService {

  @Autowired
  PlaneRevisionRepositoryPort planeRevisionRepositoryPort;

  public PlaneRevisionService(PlaneRevisionRepositoryPort planeRevisionRepositoryPort) {
    this.planeRevisionRepositoryPort = planeRevisionRepositoryPort;
  }

  public PlaneRevision createPlaneRevision(PlaneRevision planeRevision) {
    return planeRevisionRepositoryPort.save(planeRevision);
  }

  public void deletePlaneRevision(Long id) {
    planeRevisionRepositoryPort.deleteById(id);
  }

  public Optional<PlaneRevision> getPlaneRevisionById(Long id) {
    return planeRevisionRepositoryPort.findById(id);
  }

  public List<PlaneRevision> getPlaneRevisionByPlaneId(Long planeId) {
    return planeRevisionRepositoryPort.findByPlaneId(planeId);
  }

  public List<PlaneRevision> getAllPlaneRevisions() {
    return planeRevisionRepositoryPort.findAll();
  }

  public PlaneRevision updatePlaneRevision(PlaneRevision planeRevision) {
    return planeRevisionRepositoryPort.save(planeRevision);
  }

}
