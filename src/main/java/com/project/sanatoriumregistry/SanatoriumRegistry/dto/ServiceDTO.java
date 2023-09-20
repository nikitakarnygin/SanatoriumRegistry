package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.ServiceCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceDTO extends SanatoriumDTO {

    private Integer duration;

    private Integer cost;

    private ServiceCategory category;

    private List<Long> treatmentsIDs;

    private List<Long> serviceAppointmentsIDs;
}
