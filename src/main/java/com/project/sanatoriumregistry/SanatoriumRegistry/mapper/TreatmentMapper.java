package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.TreatmentDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.GenericModel;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Treatment;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.BookingInfoRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TreatmentMapper extends GenericMapper<Treatment, TreatmentDTO> {

    private final BookingInfoRepository bookingInfoRepository;
    private final ServiceRepository serviceRepository;

    protected TreatmentMapper (ModelMapper mapper,
                                BookingInfoRepository bookingInfoRepository,
                                ServiceRepository serviceRepository) {
        super(Treatment.class, TreatmentDTO.class, mapper);
        this.bookingInfoRepository = bookingInfoRepository;
        this.serviceRepository = serviceRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Treatment.class, TreatmentDTO.class)
                .addMappings(m -> m.skip(TreatmentDTO::setBookingsIDs))
                .addMappings(m -> m.skip(TreatmentDTO::setServicesIDs))
                .setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(TreatmentDTO.class, Treatment.class)
                .addMappings(m -> m.skip(Treatment::setBookings))
                .addMappings(m -> m.skip(Treatment::setServices))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(TreatmentDTO source, Treatment destination) {

        if (!Objects.isNull(source.getBookingsIDs())) {
            destination.setBookings(bookingInfoRepository.findAllById(source.getBookingsIDs()));
        } else {
            destination.setBookings(Collections.emptyList());
        }

        if (!Objects.isNull(source.getServicesIDs())) {
            destination.setServices(serviceRepository
                    .findAllById(source.getServicesIDs()));
        } else {
            destination.setServices(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Treatment source, TreatmentDTO destination) {
        destination.setBookingsIDs(getBookingsIds(source));
        destination.setServicesIDs(getServicesIds(source));
    }

    protected List<Long> getBookingsIds(Treatment entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getBookings())
                ? null
                : entity.getBookings().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }

    protected List<Long> getServicesIds(Treatment entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getServices())
                ? null
                : entity.getServices().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
