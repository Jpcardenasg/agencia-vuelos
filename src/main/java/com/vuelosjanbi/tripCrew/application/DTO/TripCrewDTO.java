package com.vuelosjanbi.tripCrew.application.DTO;

import com.vuelosjanbi.employee.application.DTO.EmployeeDTO;
import com.vuelosjanbi.flightConnection.application.DTO.FlightConnectionDTO;

public class TripCrewDTO {
  private Long employeeId;
  private Long flightConnectionId;
  private EmployeeDTO employee;
  private FlightConnectionDTO flightConnection;

  public TripCrewDTO() {
  }

  public TripCrewDTO(Long employeeId, Long flightConnectionId, EmployeeDTO employee,
      FlightConnectionDTO flightConnection) {
    this.employeeId = employeeId;
    this.flightConnectionId = flightConnectionId;
    this.employee = employee;
    this.flightConnection = flightConnection;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Long getFlightConnectionId() {
    return flightConnectionId;
  }

  public void setFlightConnectionId(Long flightConnectionId) {
    this.flightConnectionId = flightConnectionId;
  }

  public EmployeeDTO getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeDTO employee) {
    this.employee = employee;
  }

  public FlightConnectionDTO getFlightConnection() {
    return flightConnection;
  }

  public void setFlightConnection(FlightConnectionDTO flightConnection) {
    this.flightConnection = flightConnection;
  }

}
