package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_appointment_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName  = "service_appointment_info_sequence", allocationSize = 1)
public class ServiceAppointmentInfo extends GenericModel {

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "vacationer_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_SERVICE_APPOINTMENT_INFO_VACATIONERS"))
    private Vacationer vacationer;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_SERVICE_APPOINTMENT_INFO_SERVICES"))
    private Service service;
}
