package com.vuelosjanbi.seat.infraestructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.seat.application.ports.out.SeatRepositoryPort;
import com.vuelosjanbi.seat.domain.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>, SeatRepositoryPort {

}
