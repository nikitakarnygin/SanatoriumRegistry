package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "rooms_sequence", allocationSize = 1)
public class Room extends SanatoriumModel {

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "cost_per_night", nullable = false)
    private Integer costPerNight;

    @OneToMany(mappedBy = "room")
    private List<BookingInfo> bookings;
}
