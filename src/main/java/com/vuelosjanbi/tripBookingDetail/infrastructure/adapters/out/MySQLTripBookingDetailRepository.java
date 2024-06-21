package com.vuelosjanbi.tripBookingDetail.infrastructure.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.vuelosjanbi.customer.domain.models.Customer;
import com.vuelosjanbi.tripBookingDetail.application.ports.out.TripBookingDetailRepositoryPort;
import com.vuelosjanbi.tripBookingDetail.domain.models.TripBookingDetail;

public class MySQLTripBookingDetailRepository implements TripBookingDetailRepositoryPort {

  private final String url;
  private final String user;
  private final String password;

  MySQLTripBookingDetailRepository(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  @Override
  public TripBookingDetail save(TripBookingDetail tripBookingDetail) {
    try (Connection connection = DriverManager.getConnection(url, user, password)) {
      String query = "INSERT INTO trip_booking_detail (trip_booking_id, customer_id,flight_fare_id) VALUES (?, ?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setLong(1, tripBookingDetail.getTripBooking().getId());
        statement.setString(2, tripBookingDetail.getCustomer().getId());
        statement.setLong(3, tripBookingDetail.getFlightFare().getId());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tripBookingDetail;
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  @Override
  public Optional<TripBookingDetail> findByCustomer(Customer customer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByCustomer'");
  }

  @Override
  public Optional<TripBookingDetail> findByCustomerId(Customer customer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByCustomerId'");
  }

  @Override
  public Optional<TripBookingDetail> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<TripBookingDetail> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
