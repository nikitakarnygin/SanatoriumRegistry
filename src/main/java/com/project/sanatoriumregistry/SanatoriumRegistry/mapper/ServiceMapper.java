package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.GenericModel;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Service;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceAppointmentInfoRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.TreatmentRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ServiceMapper extends GenericMapper<Service, ServiceDTO> {

    private final TreatmentRepository treatmentRepository;
    private final ServiceAppointmentInfoRepository serviceAppointmentInfoRepository;

    protected ServiceMapper (ModelMapper mapper,
                             TreatmentRepository treatmentRepository,
                             ServiceAppointmentInfoRepository serviceAppointmentInfoRepository) {
        super(Service.class, ServiceDTO.class, mapper);
        this.treatmentRepository = treatmentRepository;
        this.serviceAppointmentInfoRepository = serviceAppointmentInfoRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Service.class, ServiceDTO.class)
                .addMappings(m -> m.skip(ServiceDTO::setTreatmentsIDs))
                .addMappings(m -> m.skip(ServiceDTO::setServiceAppointmentsIDs))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(ServiceDTO.class, Service.class)
                .addMappings(m -> m.skip(Service::setTreatments))
                .addMappings(m -> m.skip(Service::setServiceAppointments))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ServiceDTO source, Service destination) {
        if (!Objects.isNull(source.getTreatmentsIDs())) {
            destination.setTreatments(treatmentRepository.findAllById(source.getTreatmentsIDs()));
        } else {
            destination.setTreatments(Collections.emptyList());
        }

        if (!Objects.isNull(source.getServiceAppointmentsIDs())) {
            destination.setServiceAppointments(serviceAppointmentInfoRepository
                    .findAllById(source.getServiceAppointmentsIDs()));
        } else {
            destination.setServiceAppointments(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Service source, ServiceDTO destination) {
        destination.setTreatmentsIDs(getTreatmentsIds(source));
        destination.setServiceAppointmentsIDs(getAppointmentsIds(source));
    }

    protected List<Long> getTreatmentsIds(Service entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getTreatments())
                ? null
                : entity.getTreatments().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> getAppointmentsIds(Service entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getServiceAppointments())
                ? null
                : entity.getServiceAppointments().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
