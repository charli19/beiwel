package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserVerificationService;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.entity.UserVerificationEntity;
import com.beiwel.model.view.UserVerificationView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;

@RestController
@RequestMapping(value = URLConstant.USER_VERIFICATION)
@PreAuthorize("isAuthenticated()")
public class UserVerificationController {
    @Autowired
    public UserVerificationService userVerificationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserVerificationView.Summary.class})
    public UserEntity getUserByVerificationCode(
            @RequestParam(value = "code", required = false) String code) {
        return this.userVerificationService.getUserByCode(code);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserVerificationView.Summary.class})
    public UserVerificationEntity createUserVerification(Principal principal,
                                                         @RequestBody String email) throws MessagingException {
        return this.userVerificationService.createUserVerification(email);
    }

    @PostMapping(value = "/verification", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserVerificationView.Summary.class})
    public boolean confirmEmailVerifications(Principal principal,
                                             @RequestBody String hashCode) throws MessagingException {
        return this.userVerificationService.confirmVerification(hashCode);
    }


}