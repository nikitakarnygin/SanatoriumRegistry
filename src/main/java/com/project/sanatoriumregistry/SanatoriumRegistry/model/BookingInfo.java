package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "booking_info_sequence", allocationSize = 1)
public class BookingInfo extends GenericModel {

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_treatment_included", nullable = false)
    private Boolean isTreatmentIncluded;

    @ManyToOne
    @JoinColumn(name = "vacationer_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_BOOKING_INFO_VACATIONERS"))
    private Vacationer vacationer;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_BOOKING_INFO_ROOMS"))
    private Room room;

    @ManyToOne
    @JoinColumn(name = "treatment_id",
            foreignKey = @ForeignKey(name = "FK_BOOKING_INFO_TREATMENT"))
    private Treatment treatment;
}
