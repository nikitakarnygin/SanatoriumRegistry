package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.BookingInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceAppointmentInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.ServiceAppointmentInfo;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.VacationerRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.ServiceService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.VacationerService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
public class ServiceAppointmentInfoMapper extends GenericMapper<ServiceAppointmentInfo, ServiceAppointmentInfoDTO> {

    private final VacationerRepository vacationerRepository;
    private final ServiceRepository serviceRepository;
//    private final VacationerService vacationerService;
    private final ServiceService serviceService;

    protected ServiceAppointmentInfoMapper(ModelMapper mapper,
                                           VacationerRepository vacationerRepository,
                                           ServiceRepository serviceRepository,
                                           ServiceService serviceService) {
        super(ServiceAppointmentInfo.class, ServiceAppointmentInfoDTO.class, mapper);
        this.vacationerRepository = vacationerRepository;
        this.serviceRepository = serviceRepository;
//        this.vacationerService = vacationerService;
        this.serviceService = serviceService;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(ServiceAppointmentInfo.class, ServiceAppointmentInfoDTO.class)
                .addMappings(
                        m -> {
                            m.skip(ServiceAppointmentInfoDTO::setVacationerID);
                            m.skip(ServiceAppointmentInfoDTO::setVacationerFirstName);
                            m.skip(ServiceAppointmentInfoDTO::setVacationerSecondName);
                            m.skip(ServiceAppointmentInfoDTO::setServiceID);
//                            m.skip(ServiceAppointmentInfoDTO::setVacationerInfo);
                            m.skip(ServiceAppointmentInfoDTO::setServiceInfo);
                        })
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(ServiceAppointmentInfoDTO.class, ServiceAppointmentInfo.class)
                .addMappings(
                        m -> {
                            m.skip(ServiceAppointmentInfo::setVacationer);
                            m.skip(ServiceAppointmentInfo::setService);
                        })
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(ServiceAppointmentInfoDTO source, ServiceAppointmentInfo destination) {
        destination.setVacationer(vacationerRepository.findById(source.getVacationerID())
                .orElseThrow(() -> new NotFoundException("Отдыхающий не найден!")));
        destination.setService(serviceRepository.findById(source.getServiceID())
                .orElseThrow(() -> new NotFoundException("Услуга не найдена!")));
    }

    @Override
    protected void mapSpecificFields(ServiceAppointmentInfo source, ServiceAppointmentInfoDTO destination) {
        destination.setVacationerID(source.getVacationer().getId());
        destination.setVacationerFirstName(source.getVacationer().getFirstName());
        destination.setVacationerSecondName(source.getVacationer().getSecondName());
        destination.setServiceID(source.getService().getId());
//        destination.setVacationerInfo(vacationerService.getOne(source.getVacationer().getId()));
        destination.setServiceInfo(serviceService.getOne(source.getService().getId()));
    }
}
