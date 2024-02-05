package com.beiwel.model.entity;

import com.beiwel.model.view.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class AddressEntity extends AbstractEntity {

    @Column(nullable = false)
    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Info.class, UserView.Summary.class})
    private String street;

    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Info.class, UserView.Summary.class})
    @Column
    private String extraInfo;

    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Info.class, UserView.Summary.class})
    @Column(nullable = false)
    private String zipCode;

    @JsonView({CategoryUserView.Summary.class, ScheduleView.Summary.class,
            AppointmentView.Summary.class, AddressView.Create.class, AddressView.Summary.class,
            UserView.Summary.class, UserView.Info.class, UserView.Summary.class})
    @Column(nullable = false)
    private String city;

    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Info.class, UserView.Summary.class})
    private String province;

    @ManyToOne
    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Summary.class, UserView.Info.class, UserView.Summary.class})
    @JoinColumn(name = "location_id", nullable = true)
    private LocationEntity location;

    @ManyToOne
    @JsonView({AddressView.Create.class})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonView({CategoryUserView.Summary.class, AppointmentView.Summary.class,
            AddressView.Create.class, AddressView.Summary.class, UserView.Info.class})
    @Column
    private Boolean enabled;


    @Transient
    @JsonView({UserView.Summary.class, AddressView.Summary.class, UserView.Info.class})
    public String fullAddress() {
        String address = "";

        if (this.street != null) {
            address += this.street + " ";
            address += ( this.extraInfo == null || this.extraInfo.equals("")) ? "" : this.extraInfo + ", ";
            address += this.city == null ? "" : this.city + " ";
        }
        return address;
    }

    @Transient
    public String mapsAddress() {
        String address = "";
        if (this.street != null) {
            address += this.street + "+";
            address += ( this.extraInfo == null || this.extraInfo.equals("")) ? "" : this.extraInfo + "+";
            address += this.city == null ? "" : this.city + "+";
            address += this.zipCode == null ? "" : this.zipCode + "";
        }
        return address;
    }

}
