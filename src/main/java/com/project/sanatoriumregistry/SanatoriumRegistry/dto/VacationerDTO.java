package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.VacationerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VacationerDTO extends PersonDTO {

    private VacationerType type;

    private Boolean isSettled;

    private Long activeBookingID;

    private Boolean isEvicted;

    private List<Long> serviceAppointmentsIDs;
    private List<ServiceAppointmentInfoDTO> serviceAppointments;

    private List<Long> bookingInfoIDs;
    private List<BookingInfoDTO> bookings;
}
