package com.project.sanatoriumregistry.SanatoriumRegistry.service;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.TreatmentDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.TreatmentMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Treatment;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService extends GenericService<Treatment, TreatmentDTO> {

    protected TreatmentService(TreatmentRepository treatmentRepository, TreatmentMapper treatmentMapper) {
        super(treatmentRepository, treatmentMapper);
    }


}
