package com.project.sanatoriumregistry.SanatoriumRegistry.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "roles_sequence", allocationSize = 1)
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
