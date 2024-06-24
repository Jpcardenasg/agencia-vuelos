package com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vuelosjanbi.tripBookingDetail.application.TripBookingDetailService;

@Controller
public class TripBookingDetailConsoleAdapter {

  @Autowired
  private TripBookingDetailService tripBookingDetailService;

}
