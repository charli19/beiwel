package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.AddressService;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.AddressEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.view.AddressView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AddressController")
@RequestMapping(value = URLConstant.ADDRESS)
@PreAuthorize("isAuthenticated()")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AddressView.Summary.class})
    public List<AddressEntity> getAddressesByUserId(@PathVariable("userId") long userId) {
        return this.addressService.getAddressesByUserId(userId);
    }

    @GetMapping(value = "/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AddressView.Summary.class})
    public AddressEntity getAddressesById(@PathVariable("addressId") long addressId) {
        return this.addressService.getAddressById(addressId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AddressView.Create.class})
    public void createAddress(@RequestBody AddressEntity addressEntity) {
        UserEntity user = this.userService.getUserById(addressEntity.getUser().getId());
        addressEntity.setUser(user);
        this.addressService.createAddress(addressEntity);
    }

    @PutMapping(value = "/{adddressId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AddressView.Create.class})
    public boolean editAddress(@RequestBody AddressEntity addressEntity) {
        return this.addressService.editAddress(addressEntity);
    }

    @DeleteMapping(value = "/{addressId}")
    public void deleteAddress(@PathVariable("addressId") long addressId) {
        this.addressService.deleteAddress(addressId);
    }

}