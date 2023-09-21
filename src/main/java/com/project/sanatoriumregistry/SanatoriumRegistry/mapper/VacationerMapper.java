package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.BookingInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.GenericModel;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Vacationer;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.BookingInfoRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceAppointmentInfoRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class VacationerMapper extends GenericMapper<Vacationer, VacationerDTO> {

    private final BookingInfoRepository bookingInfoRepository;
    private final ServiceAppointmentInfoRepository serviceAppointmentInfoRepository;
    private final BookingInfoMapper bookingInfoMapper;
    private final ServiceAppointmentInfoMapper serviceAppointmentInfoMapper;

    protected VacationerMapper (ModelMapper mapper,
                                BookingInfoRepository bookingInfoRepository,
                                ServiceAppointmentInfoRepository serviceAppointmentInfoRepository,
                                BookingInfoMapper bookingInfoMapper,
                                ServiceAppointmentInfoMapper serviceAppointmentInfoMapper) {
        super(Vacationer.class, VacationerDTO.class, mapper);
        this.bookingInfoRepository = bookingInfoRepository;
        this.serviceAppointmentInfoRepository = serviceAppointmentInfoRepository;
        this.bookingInfoMapper = bookingInfoMapper;
        this.serviceAppointmentInfoMapper = serviceAppointmentInfoMapper;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Vacationer.class, VacationerDTO.class)
                .addMappings(m -> m.skip(VacationerDTO::setBookingInfoIDs))
                .addMappings(m -> m.skip(VacationerDTO::setServiceAppointmentsIDs))
                .addMappings(m -> m.skip(VacationerDTO::setBookings))
                .addMappings(m -> m.skip(VacationerDTO::setServiceAppointments))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(VacationerDTO.class, Vacationer.class)
                .addMappings(m -> m.skip(Vacationer::setBookingInfos))
                .addMappings(m -> m.skip(Vacationer::setServiceAppointments))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(VacationerDTO source, Vacationer destination) {
        if (!Objects.isNull(source.getBookingInfoIDs())) {
            destination.setBookingInfos(bookingInfoRepository.findAllById(source.getBookingInfoIDs()));
        } else {
            destination.setBookingInfos(Collections.emptyList());
        }

        if (!Objects.isNull(source.getServiceAppointmentsIDs())) {
            destination.setServiceAppointments(serviceAppointmentInfoRepository
                    .findAllById(source.getServiceAppointmentsIDs()));
        } else {
            destination.setServiceAppointments(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Vacationer source, VacationerDTO destination) {
        destination.setBookingInfoIDs(getBookingsIds(source));
        destination.setServiceAppointmentsIDs(getAppointmentsIds(source));
        destination.setBookings(bookingInfoMapper.toDTOs(source.getBookingInfos()));
        destination.setServiceAppointments(serviceAppointmentInfoMapper.toDTOs(source.getServiceAppointments()));
    }

    protected List<Long> getBookingsIds(Vacationer entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getBookingInfos())
                ? null
                : entity.getBookingInfos().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> getAppointmentsIds(Vacationer entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getServiceAppointments())
                ? null
                : entity.getServiceAppointments().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
