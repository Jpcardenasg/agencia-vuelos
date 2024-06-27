package com.vuelosjanbi.seat.application.ports.out;

import java.util.Optional;

import com.vuelosjanbi.seat.domain.Seat;

public interface SeatRepositoryPort {

  Seat save(Seat seat);

  Optional<Seat> findById(Long id);

  void deleteById(Long id);

  Optional<Seat> findBySeatNumber(String seatNumber);

}
