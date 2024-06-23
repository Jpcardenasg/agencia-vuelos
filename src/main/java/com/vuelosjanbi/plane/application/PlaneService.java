package com.vuelosjanbi.plane.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepositoryPort planeRepositoryPort;

    public PlaneService(PlaneRepositoryPort planeRepositoryPort) {
        this.planeRepositoryPort = planeRepositoryPort;
    }

    public Plane createPlane(Plane plane) {
        return planeRepositoryPort.save(plane);
    }

    public Plane getPlane(Long planeId) {
        return planeRepositoryPort.findById(planeId).orElse(null);
    }

    public Plane updatePlane(Plane plane) {
        return planeRepositoryPort.save(plane);
    }

    public void deletePlane(Long planeId) {
        planeRepositoryPort.deleteById(planeId);
    }

    public List<Plane> getAllPlanes() {
        return planeRepositoryPort.findAll();
    }
}
