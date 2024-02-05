package com.beiwel.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDto {
    public long id;
    public String title;
    public Double price;
    public Boolean lowestPrice;
    public Integer duration;
    public Boolean enabled;
    public String description;
    public String guid;
    private SessionTypeDto sessionType;
}
