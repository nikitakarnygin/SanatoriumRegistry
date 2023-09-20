package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookingInfoDTO extends GenericDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isTreatmentIncluded;

    private Boolean isVacationerSettled;

    private Boolean isVacationerEvicted;

    private Long vacationerActiveBooking;

    private Long vacationerID;

//    private VacationerDTO vacationerInfo;

    private String vacationerFirstName;
    private String vacationerSecondName;

    private Long roomID;

    private RoomDTO roomInfo;

    private Long treatmentID;

    private TreatmentDTO treatmentInfo;
}
