package com.project.sanatoriumregistry.SanatoriumRegistry.service;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.VacationerMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Vacationer;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.VacationerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationerService extends GenericService<Vacationer, VacationerDTO> {

    protected VacationerService(VacationerRepository vacationerRepository,
                                VacationerMapper vacationerMapper) {
        super(vacationerRepository, vacationerMapper);
    }

    public Page<VacationerDTO> getAllVacationers(Pageable pageable) {
        Page<Vacationer> vacationersPaginated = repository.findAll(pageable); //TODO: Переделать отображение для admin, библиотекаря и для пользователя
        List<VacationerDTO> result = mapper.toDTOs(vacationersPaginated.getContent());
        return new PageImpl<>(result, pageable, vacationersPaginated.getTotalElements());
    }

    public Page<VacationerDTO> searchVacationer(VacationerDTO vacationerDTO,
                                                Pageable pageRequest) {

        Page<Vacationer> vacationersPaginated = ((VacationerRepository) repository).searchVacationers(
                vacationerDTO.getFirstName(),
                vacationerDTO.getSecondName(),
                vacationerDTO.getPhone(),
                pageRequest
        );
        List<VacationerDTO> result = mapper.toDTOs(vacationersPaginated.getContent());
        return new PageImpl<>(result, pageRequest, vacationersPaginated.getTotalElements());
    }
}
