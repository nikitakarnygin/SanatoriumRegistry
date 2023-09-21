package com.project.sanatoriumregistry.SanatoriumRegistry.service;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceAppointmentInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.StartAndEndDateFilterDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.ServiceAppointmentInfoMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.ServiceAppointmentInfo;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceAppointmentInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceAppointmentInfoService extends GenericService<ServiceAppointmentInfo, ServiceAppointmentInfoDTO> {

    protected ServiceAppointmentInfoService(ServiceAppointmentInfoRepository serviceAppointmentInfoRepository,
                                            ServiceAppointmentInfoMapper serviceAppointmentInfoMapper) {
        super(serviceAppointmentInfoRepository, serviceAppointmentInfoMapper);
    }

    public Page<ServiceAppointmentInfoDTO> getAllAppointments(Pageable pageable) {
        Page<ServiceAppointmentInfo> appointmentsPaginated = repository.findAll(pageable);
        List<ServiceAppointmentInfoDTO> result = mapper.toDTOs(appointmentsPaginated.getContent());
        return new PageImpl<>(result, pageable, appointmentsPaginated.getTotalElements());
    }

    public ServiceAppointmentInfoDTO makeAppointment(final ServiceAppointmentInfoDTO serviceAppointmentInfoDTO) {
//        TODO serviceAppointmentInfoDTO.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        serviceAppointmentInfoDTO.setCreatedWhen(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(serviceAppointmentInfoDTO)));
    }

    public void cancelAppointment (final Long appointmentID) {
        delete(appointmentID);
    }

    public Page<ServiceAppointmentInfoDTO> filterAppointmentsByDate(StartAndEndDateFilterDTO filterDTO,
                                                                    Pageable pageRequest) {

        Page<ServiceAppointmentInfo> appointmentsPaginated = ((ServiceAppointmentInfoRepository) repository)
                .filterAppointments(
                filterDTO.getStartDate(),
                filterDTO.getEndDate(),
                pageRequest
        );
        List<ServiceAppointmentInfoDTO> result = mapper.toDTOs(appointmentsPaginated.getContent());
        return new PageImpl<>(result, pageRequest, appointmentsPaginated.getTotalElements());
    }

    public Page<ServiceAppointmentInfoDTO> searchAppointments(VacationerDTO vacationerDTO,
                                                                    Pageable pageRequest) {

        Page<ServiceAppointmentInfo> appointmentsPaginated = ((ServiceAppointmentInfoRepository) repository)
                .searchAppointments(
                        vacationerDTO.getFirstName(),
                        vacationerDTO.getSecondName(),
                        vacationerDTO.getPhone(),
                        pageRequest
                );
        List<ServiceAppointmentInfoDTO> result = mapper.toDTOs(appointmentsPaginated.getContent());
        return new PageImpl<>(result, pageRequest, appointmentsPaginated.getTotalElements());
    }
}
