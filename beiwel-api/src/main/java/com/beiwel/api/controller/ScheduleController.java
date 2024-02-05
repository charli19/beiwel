package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.ScheduleService;
import com.beiwel.model.entity.ScheduleEntity;
import com.beiwel.model.view.ScheduleView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("ScheduleController")
@RequestMapping(value = URLConstant.SCHEDULE)
@PreAuthorize("isAuthenticated()")
public class ScheduleController {

  @Autowired
  private ScheduleService scheduleService;

  @GetMapping(value = "/disponibility/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> getDisponibilityById(
      @PathVariable("sessionId") long sessionId,
      @RequestParam("date") String date) {
    return scheduleService.getScheduleListBySessionId(sessionId, date);
  }

  @GetMapping(value = "/week/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> getWeekForScheduleListBySessionId(
      @PathVariable("sessionId") long sessionId,
      @RequestParam("date") String date) {
    return scheduleService.getWeekForScheduleListBySessionId(sessionId, date);
  }

  @GetMapping(value = "/month/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> getMonthForScheduleListBySessionId(
      @PathVariable("sessionId") long sessionId,
      @RequestParam("date") String date) {
    return scheduleService.getMonthForScheduleListBySessionId(sessionId, date);
  }

  @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonView({ScheduleView.Summary.class})
  public List<ScheduleEntity> getScheduleListByUserId(
      @PathVariable("userId") long userId) {
    return scheduleService.getScheduleListByUserId(userId);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @JsonView({ScheduleView.Create.class})
  public ScheduleEntity createSchedule(
      @RequestBody ScheduleEntity scheduleEntity) {

    return this.scheduleService.createSchedule(scheduleEntity);
  }

  @PutMapping(value = "/{scheduleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ScheduleEntity editScheduleCategoryUser(
      @RequestBody ScheduleEntity scheduleEntity) {
    return this.scheduleService.editSchedule(scheduleEntity);
  }

  @DeleteMapping(value = "/{scheduleId}")
  public void deleteSchedule(
      @PathVariable("scheduleId") long scheduleId) {
    this.scheduleService.deleteSchedule(scheduleId);
  }
}
