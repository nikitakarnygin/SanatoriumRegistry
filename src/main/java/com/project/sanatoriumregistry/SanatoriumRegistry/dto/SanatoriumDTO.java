package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class SanatoriumDTO extends GenericDTO {

    private String title;

    private String description;
}
