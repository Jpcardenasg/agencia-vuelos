package com.vuelosjanbi.plane.application;

import java.util.List;

import com.vuelosjanbi.plane.application.ports.out.PlaneRepositoryPort;
import com.vuelosjanbi.plane.domain.models.Plane;

public class PlaneService {

    private final PlaneRepositoryPort planeRepositoryPort;

    public PlaneService(PlaneRepositoryPort planeRepositoryPort) {
        this.planeRepositoryPort = planeRepositoryPort;
    }

    public Plane createPlane(Plane plane) {
        return planeRepositoryPort.save(plane);
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
