package com.beiwel.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentWebappDTO {

  public AppointmentDTO service;

  public UserDTO personalInformation;

  public AddressDTO address;

  public boolean isValid()
  {
    if (this.getService().getDate() == null) return false;
    if (this.getService().getTime() == null) return false;
    if (this.getService().getSessionId() == null) return false;
    return true;
  }

}
