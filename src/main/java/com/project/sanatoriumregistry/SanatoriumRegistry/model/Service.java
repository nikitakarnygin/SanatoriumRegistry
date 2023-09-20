package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName  = "services_sequence", allocationSize = 1)
public class Service extends SanatoriumModel {

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceCategory category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "treatments_services",
            joinColumns = @JoinColumn(name = "service_id"),
            foreignKey = @ForeignKey(name = "FK_SERVICES_TREATMENTS"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id"),
            inverseForeignKey = @ForeignKey(name = "FK_TREATMENTS_SERVICES"))
    List<Treatment> treatments;

    @OneToMany(mappedBy = "service")
    private List<ServiceAppointmentInfo> serviceAppointments;
}
