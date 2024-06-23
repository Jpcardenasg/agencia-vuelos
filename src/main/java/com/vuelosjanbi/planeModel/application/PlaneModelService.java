package com.vuelosjanbi.planeModel.application;

import com.vuelosjanbi.planeModel.application.ports.out.PlaneModelRepositoryPort;
import com.vuelosjanbi.planeModel.domain.models.PlaneModel;

import java.util.Optional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneModelService {

  private PlaneModelRepositoryPort planeModelRepositoryPort;

  public PlaneModelService(PlaneModelRepositoryPort planeModelRepositoryPort) {
    this.planeModelRepositoryPort = planeModelRepositoryPort;
  }

  public PlaneModel createPlaneModel(PlaneModel planeModel) {
    return planeModelRepositoryPort.save(planeModel);
  }

  public Optional<PlaneModel> getPlaneModelById(Long id) {
    return planeModelRepositoryPort.findById(id);
  }

  public PlaneModel updatePlaneModel(PlaneModel planeModel) {
    return planeModelRepositoryPort.save(planeModel);
  }

  public void deletePlaneModel(Long id) {
    planeModelRepositoryPort.deleteById(id);
  }

  public List<PlaneModel> getAllPlaneModels() {
    return planeModelRepositoryPort.findAll();
  }
}
