package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AppointmentSearchDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
