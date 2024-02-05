package com.beiwel.model.dto;

import com.beiwel.model.entity.AddressEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public long id;
    public String name;
    public String surname;
    public String phoneNumber;
    public String email;
    public String nickname;
    public Boolean acceptedTerms;
    public Boolean acceptedMarketing;
    public Boolean isGuest;
    public String password;
    public List<ScheduleDto> schedule;
    public List<SessionDto> sessions;
    public List<AddressDTO> address;

    public boolean isValid()
    {
        if (this.getIsGuest() == null) return false;
        if (this.getEmail() == null) return false;
        if (this.getName() == null) return false;
        return true;
    }
}
