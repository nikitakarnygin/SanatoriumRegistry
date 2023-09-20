package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.BookingInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.BookingInfo;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.RoomRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.TreatmentRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.VacationerRepository;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.RoomService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.TreatmentService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
public class BookingInfoMapper extends GenericMapper<BookingInfo, BookingInfoDTO> {

    private final VacationerRepository vacationerRepository;
    private final RoomRepository roomRepository;
    private final TreatmentRepository treatmentRepository;
//    private final VacationerService vacationerService;
    private final RoomService roomService;
    private final TreatmentService treatmentService;

    protected BookingInfoMapper(ModelMapper mapper,
                                VacationerRepository vacationerRepository,
                                RoomRepository roomRepository,
                                TreatmentRepository treatmentRepository,
                                RoomService roomService,
                                TreatmentService treatmentService) {
        super(BookingInfo.class, BookingInfoDTO.class, mapper);
        this.vacationerRepository = vacationerRepository;
        this.roomRepository = roomRepository;
        this.treatmentRepository = treatmentRepository;
//        this.vacationerService = vacationerService;
        this.roomService = roomService;
        this.treatmentService = treatmentService;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(BookingInfo.class, BookingInfoDTO.class)
                .addMappings(
                        m -> {
                            m.skip(BookingInfoDTO::setVacationerID);
                            m.skip(BookingInfoDTO::setVacationerFirstName);
                            m.skip(BookingInfoDTO::setVacationerSecondName);
                            m.skip(BookingInfoDTO::setIsVacationerSettled);
                            m.skip(BookingInfoDTO::setIsVacationerEvicted);
                            m.skip(BookingInfoDTO::setVacationerActiveBooking);
                            m.skip(BookingInfoDTO::setRoomID);
                            m.skip(BookingInfoDTO::setTreatmentID);
//                            m.skip(BookingInfoDTO::setVacationerInfo);
                            m.skip(BookingInfoDTO::setRoomInfo);
                            m.skip(BookingInfoDTO::setTreatmentInfo);
                        })
                .setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(BookingInfoDTO.class, BookingInfo.class)
                .addMappings(
                        m -> {
                            m.skip(BookingInfo::setVacationer);
                            m.skip(BookingInfo::setRoom);
                            m.skip(BookingInfo::setTreatment);
                        })
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(BookingInfoDTO source, BookingInfo destination) {
        destination.setVacationer(vacationerRepository.findById(source.getVacationerID())
                .orElseThrow(() -> new NotFoundException("Отдыхающий не найден!")));
        destination.setRoom(roomRepository.findById(source.getRoomID())
                .orElseThrow(() -> new NotFoundException("Тип размещения не найден!")));
        destination.setTreatment(treatmentRepository.findById(source.getTreatmentID())
                .orElseThrow(() -> new NotFoundException("Тип лечения не найден!")));
    }

    @Override
    protected void mapSpecificFields(BookingInfo source, BookingInfoDTO destination) {
        destination.setVacationerID(source.getVacationer().getId());
        destination.setVacationerFirstName(source.getVacationer().getFirstName());
        destination.setVacationerSecondName(source.getVacationer().getSecondName());
        destination.setIsVacationerSettled(source.getVacationer().getIsSettled());
        destination.setIsVacationerEvicted(source.getVacationer().getIsEvicted());
        destination.setVacationerActiveBooking(source.getVacationer().getActiveBookingID());
        destination.setRoomID(source.getRoom().getId());
        if (source.getIsTreatmentIncluded()) {
            destination.setTreatmentID(source.getTreatment().getId());
            destination.setTreatmentInfo(treatmentService.getOne(source.getTreatment().getId()));
        }
//        destination.setVacationerInfo(vacationerService.getOne(source.getVacationer().getId()));
        destination.setRoomInfo(roomService.getOne(source.getRoom().getId()));
    }
}