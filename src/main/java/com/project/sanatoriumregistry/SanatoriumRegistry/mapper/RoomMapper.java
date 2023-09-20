package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.RoomDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.GenericModel;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Room;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.BookingInfoRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoomMapper extends GenericMapper<Room, RoomDTO> {

    private final BookingInfoRepository bookingInfoRepository;

    protected RoomMapper(ModelMapper mapper,
                         BookingInfoRepository bookingInfoRepository) {
        super(Room.class, RoomDTO.class, mapper);
        this.bookingInfoRepository = bookingInfoRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Room.class, RoomDTO.class)
                .addMappings(m -> m.skip(RoomDTO::setBookingsIDs))
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(RoomDTO.class, Room.class)
                .addMappings(m -> m.skip(Room::setBookings)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(RoomDTO source, Room destination) {
        if (!Objects.isNull(source.getBookingsIDs())) {
            destination.setBookings(bookingInfoRepository.findAllById(source.getBookingsIDs()));
        }
        else {
            destination.setBookings(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Room source, RoomDTO destination) {
        destination.setBookingsIDs(getBookingsIds(source));
    }

    protected List<Long> getBookingsIds(Room entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getBookings())
                ? null
                : entity.getBookings().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
