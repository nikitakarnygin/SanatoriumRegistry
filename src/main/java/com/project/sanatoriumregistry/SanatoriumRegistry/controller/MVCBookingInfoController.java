package com.project.sanatoriumregistry.SanatoriumRegistry.controller;


import com.project.sanatoriumregistry.SanatoriumRegistry.dto.BookingInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceAppointmentInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.StartAndEndDateFilterDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.BookingInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/bookings")
public class MVCBookingInfoController {

    private final BookingInfoService bookingInfoService;

    protected MVCBookingInfoController(BookingInfoService bookingInfoService) {
        this.bookingInfoService = bookingInfoService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "createdWhen"));
        Page<BookingInfoDTO> bookings  = bookingInfoService.getAllBookings(pageRequest);
        model.addAttribute("bookings", bookings);
        model.addAttribute("exception", exception);
        return "bookings/viewAllBookings";
    }

    @PostMapping("/filter")
    public String filterBookings(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                 @ModelAttribute("bookingFilterForm") StartAndEndDateFilterDTO filterDTO,
                                 Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "created_when"));
        model.addAttribute("bookings",
                bookingInfoService.filterBookingsByStartDate(filterDTO, pageRequest));
        return "bookings/viewAllBookings";
    }

    @PostMapping("/search")
    public String searchBookings(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                 @ModelAttribute("bookingsSearchForm") VacationerDTO vacationerDTO,
                                 Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "created_when"));
        model.addAttribute("bookings",
                bookingInfoService.searchBookings(vacationerDTO, pageRequest));
        return "bookings/viewAllBookings";
    }

//    @GetMapping("/cancel/{bookingID}")
//    public String cancelBooking(@PathVariable Long bookingID) {
//        bookingInfoService.cancelBooking(bookingID);
//        return "redirect:/bookings";
//    }

    @GetMapping("/settle-vacationer/{bookingID}")
    public String settleVacationer(@PathVariable Long bookingID) {
        bookingInfoService.settleVacationer(bookingID);
        return "redirect:/bookings";
    }

    @GetMapping("/unsettle-vacationer/{bookingID}")
    public String unsettleVacationer(@PathVariable Long bookingID) {
        bookingInfoService.unsettleVacationer(bookingID);
        return "redirect:/bookings";
    }
}
