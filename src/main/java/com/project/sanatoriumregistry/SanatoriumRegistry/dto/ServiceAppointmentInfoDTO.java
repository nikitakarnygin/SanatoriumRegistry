package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceAppointmentInfoDTO extends GenericDTO {

    private LocalDateTime date;

    private Long vacationerID;

    private Long serviceID;

//    private VacationerDTO vacationerInfo;

    private String vacationerFirstName;
    private String vacationerSecondName;

    private ServiceDTO serviceInfo;

    public ServiceAppointmentInfoDTO(LocalDateTime date, Long vacationerID, Long serviceID) {
        this.date = date;
        this.vacationerID = vacationerID;
        this.serviceID = serviceID;
    }
}
