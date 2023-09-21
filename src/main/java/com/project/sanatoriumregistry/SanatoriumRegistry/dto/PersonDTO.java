package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public abstract class PersonDTO extends GenericDTO {

    private String firstName;

    private String secondName;

    private String middleName;

    private LocalDate birthDate;

    private String phone;

    private String email;
}
