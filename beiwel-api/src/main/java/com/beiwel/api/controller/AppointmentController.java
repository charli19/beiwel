package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.AppointmentService;
import com.beiwel.model.dto.AppointmentWebappDTO;
import com.beiwel.model.entity.AppointmentEntity;
import com.beiwel.model.filter.AppointmentFilter;
import com.beiwel.model.pagination.AppointmentPagination;
import com.beiwel.model.view.AppointmentView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController("AppointmentController")
@RequestMapping(value = URLConstant.APPOINTMENTS)
@PreAuthorize("isAuthenticated()")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public AppointmentEntity getAppointmentById(@PathVariable("appointmentId") long appointmentId) {

        return this.appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping(value = "/hash/{hash}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public AppointmentEntity getAppointmentByHash(@PathVariable("hash") String hash) {

        return this.appointmentService.getAppointmentByHash(hash);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public AppointmentPagination getAppointmentList(
            @RequestParam(value = "clientUserId", required = false) Long clientUserId,
            @RequestParam(value = "sessionId", required = false) Long sessionId,
            @RequestParam(value = "businessUserId", required = false) Long businessUserId,
            @RequestParam(value = "statusIds", required = false) List<Long> statusIds,
            @RequestParam(value = "orderReverse", required = false) Boolean orderReverse,
            @RequestParam(value = "manual", required = false) Boolean manual,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        AppointmentFilter appointmentFilter = appointmentService.mapAppointmentFilter(page, size,
                clientUserId, sessionId, businessUserId, statusIds, orderReverse, manual);

        return this.appointmentService.getAppointmentList(appointmentFilter);
    }

    @GetMapping(value = "/calendar/{userId}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<LocalDate, List<String[]>> getAppointmentByUserId(@PathVariable("userId") long userId,
                                                                 @PathVariable("date") String date) {
        return appointmentService.getAppointmentByUserId(userId, date);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public AppointmentEntity createAppointment(@RequestBody AppointmentEntity appointmentEntity)
            throws MessagingException, IOException {

        return this.appointmentService.createAppointment(appointmentEntity);
    }

    @PostMapping(value = "/webapp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public AppointmentEntity createAppointmentWebapp(@RequestBody AppointmentWebappDTO appointmentWebappDTO) throws Exception {

        return this.appointmentService.createAppointmentWebApp(appointmentWebappDTO);
    }

    @PostMapping(value = "/asignClient", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public boolean asignAppointment(
            @RequestParam("appointmentId") long appointmentId,
            @RequestParam("email") String email,
            @RequestParam("name") String name) throws Exception {

        return this.appointmentService.asignAppointment(appointmentId, email, name);
    }

    @PutMapping(value = "/{appointmentId}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({AppointmentView.Summary.class})
    public boolean editAppointment(@RequestBody AppointmentEntity appointmentEntity, @PathVariable("userId") Long userId) throws MessagingException {
        return this.appointmentService.editAppointment(appointmentEntity, userId);
    }

}