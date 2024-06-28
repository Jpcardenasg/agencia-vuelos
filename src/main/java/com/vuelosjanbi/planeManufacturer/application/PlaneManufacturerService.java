package com.vuelosjanbi.planeManufacturer.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.planeManufacturer.application.ports.out.PlaneManufacturerRepositoryPort;
import com.vuelosjanbi.planeManufacturer.domain.models.PlaneManufacturer;

@Service
public class PlaneManufacturerService {

  @Autowired
  PlaneManufacturerRepositoryPort planeManufacturerRepositoryPort;

  public PlaneManufacturer createPlaneManufacturer(PlaneManufacturer planeManufacturer) {
    return planeManufacturerRepositoryPort.save(planeManufacturer);
  }

  public PlaneManufacturer updatePlaneManufacturer(PlaneManufacturer planeManufacturer) {
    return planeManufacturerRepositoryPort.save(planeManufacturer);
  }

  public void deletePlaneManufacturer(Long id) {
    planeManufacturerRepositoryPort.deleteById(id);
  }

  public Optional<PlaneManufacturer> getPlaneManufacturerById(Long id) {
    return planeManufacturerRepositoryPort.findById(id);
  }

  public Optional<PlaneManufacturer> getPlaneManufacturerByName(String name) {
    return planeManufacturerRepositoryPort.findByName(name);
  }

  public List<PlaneManufacturer> getAllManufacturers() {
    return planeManufacturerRepositoryPort.findAll();
  }
}
