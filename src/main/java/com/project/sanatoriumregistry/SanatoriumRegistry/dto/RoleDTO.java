package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {

    private Long id;
    private String title;
    private String description;
}
