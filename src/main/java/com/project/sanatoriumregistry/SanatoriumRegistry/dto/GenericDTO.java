package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericDTO {

    private Long id;
    private LocalDateTime createdWhen;
    private String createdBy;
    private Boolean isDeleted;
    private LocalDateTime deletedWhen;
    private String deletedBy;
}
