package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.SessionService;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.SessionEntity;
import com.beiwel.model.filter.SessionFilter;
import com.beiwel.model.pagination.SessionPagination;
import com.beiwel.model.view.SessionView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("SessionController")
@RequestMapping(value = URLConstant.SESSION)
public class SessionController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({SessionView.Summary.class})
    @PreAuthorize("isAuthenticated()")
    public SessionEntity getSessionById(@PathVariable("sessionId") long sessionId) {
        return this.sessionService.getSessionById(sessionId);
    }

    @GetMapping(value = "/guid/{guid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({SessionView.Summary.class})
    @PreAuthorize("isAuthenticated()")
    public SessionEntity getSessionByGuid(@PathVariable("guid") String guid) {
        return this.sessionService.getSessionByGuid(guid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({SessionView.Summary.class})
    public SessionPagination getSessionList(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        SessionFilter sessionFilter = sessionService.mapSessionFilter(page, size, userId, enabled);
        return this.sessionService.getSessionList(sessionFilter);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({SessionView.Summary.class})
    @PreAuthorize("isAuthenticated()")
    public void createSession(@RequestBody SessionEntity sessionEntity) {
        this.sessionService.createSession(sessionEntity);
    }

    @PutMapping(value = "/{sessionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({SessionView.Summary.class})
    @PreAuthorize("isAuthenticated()")
    public SessionEntity editSession(@RequestBody SessionEntity sessionEntity) throws Exception {
        return this.sessionService.editSession(sessionEntity);
    }

    @DeleteMapping(value = "/{sessionId}")
    @PreAuthorize("isAuthenticated()")
    public void deleteSession(@PathVariable("sessionId") long sessionId) {
        this.sessionService.deleteSession(sessionId);
    }

}