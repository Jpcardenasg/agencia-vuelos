package com.vuelosjanbi.trip.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;

import com.vuelosjanbi.trip.application.TripService;

public class TripConsoleAdapter {

  @Autowired
  private TripService tripService;

  public void start() {
    System.out.println("Trip Console Adapter started");
  }

}
