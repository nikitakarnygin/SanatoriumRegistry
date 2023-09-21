package com.project.sanatoriumregistry.SanatoriumRegistry.service;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.RoomDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.RoomMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Room;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends GenericService<Room, RoomDTO> {

    protected RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        super(roomRepository, roomMapper);
    }
}
