package com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuelosjanbi.tripBookingDetail.application.ports.out.TripBookingDetailRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

@Repository
@Primary
public interface TripBookingDetailRepository
        extends JpaRepository<TripBookingDetail, Long>, TripBookingDetailRepositoryPort {

}
