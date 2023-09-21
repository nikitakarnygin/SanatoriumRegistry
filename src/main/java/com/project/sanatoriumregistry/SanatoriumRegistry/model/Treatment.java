package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "treatments_sequence", allocationSize = 1)
public class Treatment extends SanatoriumModel {

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToMany(mappedBy = "treatment")
    private List<BookingInfo> bookings;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "treatments_services",
            joinColumns = @JoinColumn(name = "treatment_id"),
            foreignKey = @ForeignKey(name = "FK_TREATMENTS_SERVICES"),
            inverseJoinColumns = @JoinColumn(name = "service_id"),
            inverseForeignKey = @ForeignKey(name = "FK_SERVICES_TREATMENTS"))
    List<Service> services;
}
