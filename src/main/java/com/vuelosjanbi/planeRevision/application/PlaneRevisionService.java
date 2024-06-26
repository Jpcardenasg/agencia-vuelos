package com.vuelosjanbi.planeRevision.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.vuelosjanbi.planeRevision.domain.models.PlaneRevision;
import com.vuelosjanbi.planeRevision.infrastructure.adapters.out.PlaneRevisionRepository;

@Service
public class PlaneRevisionService {

  @Autowired
  private PlaneRevisionRepository planeRevisionRepository;

  public PlaneRevisionService(PlaneRevisionRepository planeRevisionRepository) {
    this.planeRevisionRepository = planeRevisionRepository;
  }

  public PlaneRevision createPlaneRevision(PlaneRevision planeRevision) {
    return planeRevisionRepository.save(planeRevision);
  }

  public PlaneRevision getPlaneRevisionById(Long id) {
    return planeRevisionRepository.findById(id).orElse(null);
  }

  public PlaneRevision updatePlaneRevision(PlaneRevision planeRevision) {
    return planeRevisionRepository.save(planeRevision);
  }

  public void deletePlaneRevision(Long id) {
    planeRevisionRepository.deleteById(id);
  }

  public List<PlaneRevision> getAllPlaneRevisions() {
    return planeRevisionRepository.findAll();
  }

}
