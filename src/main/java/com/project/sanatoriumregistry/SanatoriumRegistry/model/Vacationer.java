package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vacationers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "vacationers_sequence", allocationSize = 1)
public class Vacationer extends PersonModel {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VacationerType type;

    @Column(name = "is_settled", nullable = false)
    private Boolean isSettled;

    @Column(name = "active_booking_id")
    private Long activeBookingID;

    @Column(name = "is_evicted", nullable = false)
    private Boolean isEvicted;

    @OneToMany(mappedBy = "vacationer")
    private List<BookingInfo> bookingInfos;

    @OneToMany(mappedBy = "vacationer")
    private List<ServiceAppointmentInfo> serviceAppointments;
}
