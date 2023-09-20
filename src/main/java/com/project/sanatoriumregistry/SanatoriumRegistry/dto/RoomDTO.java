package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoomDTO extends SanatoriumDTO {

    private Integer amount;

    private Integer capacity;

    private Integer costPerNight;

    private List<Long> bookingsIDs;
}
