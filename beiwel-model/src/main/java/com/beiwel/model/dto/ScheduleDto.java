package com.beiwel.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
    public Long day;
    public String startTime;
    public String endTime;
}
