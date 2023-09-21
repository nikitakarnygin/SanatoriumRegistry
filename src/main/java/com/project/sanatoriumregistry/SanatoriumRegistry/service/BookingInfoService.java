package com.project.sanatoriumregistry.SanatoriumRegistry.service;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.BookingInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.StartAndEndDateFilterDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.BookingInfoMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.BookingInfo;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.BookingInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingInfoService extends GenericService<BookingInfo, BookingInfoDTO> {

    private final VacationerService vacationerService;
    protected BookingInfoService(BookingInfoRepository bookingInfoRepository,
                                 BookingInfoMapper bookingInfoMapper,
                                 VacationerService vacationerService) {
        super(bookingInfoRepository, bookingInfoMapper);
        this.vacationerService = vacationerService;
    }

    public Page<BookingInfoDTO> getAllBookings(Pageable pageable) {
        Page<BookingInfo> bookingsPaginated = repository.findAll(pageable);
        List<BookingInfoDTO> result = mapper.toDTOs(bookingsPaginated.getContent());
        return new PageImpl<>(result, pageable, bookingsPaginated.getTotalElements());
    }

    public Page<BookingInfoDTO> filterBookingsByStartDate(StartAndEndDateFilterDTO filterDTO,
                                                          Pageable pageRequest) {

        Page<BookingInfo> bookingsPaginated = ((BookingInfoRepository) repository)
                .filterBookings(
                        filterDTO.getStartDate(),
                        filterDTO.getEndDate(),
                        pageRequest
                );
        List<BookingInfoDTO> result = mapper.toDTOs(bookingsPaginated.getContent());
        return new PageImpl<>(result, pageRequest, bookingsPaginated.getTotalElements());
    }

    public Page<BookingInfoDTO> searchBookings(VacationerDTO vacationerDTO,
                                               Pageable pageRequest) {

        Page<BookingInfo> bookingsPaginated = ((BookingInfoRepository) repository)
                .searchBookings(
                        vacationerDTO.getFirstName(),
                        vacationerDTO.getSecondName(),
                        vacationerDTO.getPhone(),
                        pageRequest
                );
        List<BookingInfoDTO> result = mapper.toDTOs(bookingsPaginated.getContent());
        return new PageImpl<>(result, pageRequest, bookingsPaginated.getTotalElements());
    }

//    public void cancelBooking(final Long bookingID) {
//        delete(bookingID);
//    }

    public void settleVacationer(final Long bookingID) {
        BookingInfoDTO booking = getOne(bookingID);
        VacationerDTO vacationer = vacationerService.getOne(booking.getVacationerID());
        vacationer.setIsSettled(true);
        vacationer.setActiveBookingID(bookingID);
        vacationerService.update(vacationer);
    }

    public void unsettleVacationer(final Long bookingID) {
        BookingInfoDTO booking = getOne(bookingID);
        VacationerDTO vacationer = vacationerService.getOne(booking.getVacationerID());
        vacationer.setIsSettled(false);
        vacationer.setActiveBookingID(null);
        vacationer.setIsEvicted(true);
        vacationerService.update(vacationer);
    }

}
