package com.beiwel.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentDTO {

  private Long id;

  private LocalDate date;

  private LocalTime time;

  private String observation;

  private Long sessionId;


}
