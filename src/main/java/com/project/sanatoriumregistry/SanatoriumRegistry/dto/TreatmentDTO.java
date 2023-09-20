package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TreatmentDTO extends SanatoriumDTO {

    private Integer duration;

    private List<Long> bookingsIDs;

    private List<Long> servicesIDs;
}
