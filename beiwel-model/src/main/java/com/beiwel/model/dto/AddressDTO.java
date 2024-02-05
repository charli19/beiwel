package com.beiwel.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

  public long id;
  public String street;
  public String extraInfo;
  public String zipCode;
  public String city;
  public String province;
  public String latitude;
  public String longitude;
  public long userId;

  @JsonProperty
  public String fullAddress() {
    String address = "";

    if (this.street != null) {
      address += this.street + " ";
      address += this.extraInfo == null ? "" : this.extraInfo + ", ";
      address += this.city == null ? "" : this.city + " ";
    }

    return address;
  }


  public boolean isValid()
  {
    if (this.getStreet() == null) return false;
    if (this.getCity() == null) return false;
    if (this.getZipCode() == null) return false;
    return true;
  }
}
