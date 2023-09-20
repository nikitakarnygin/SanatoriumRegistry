package com.project.sanatoriumregistry.SanatoriumRegistry.controller;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceAppointmentInfoDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.StartAndEndDateFilterDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.ServiceAppointmentInfoService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.ServiceService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.VacationerService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.userdetails.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/appointments")
public class MVCServiceAppointmentInfoController {

    private final ServiceAppointmentInfoService serviceAppointmentInfoService;
    private final VacationerService vacationerService;
    private final ServiceService serviceService;

    public MVCServiceAppointmentInfoController(ServiceAppointmentInfoService serviceAppointmentInfoService,
                                               VacationerService vacationerService,
                                               ServiceService serviceService) {
        this.serviceAppointmentInfoService = serviceAppointmentInfoService;
        this.vacationerService = vacationerService;
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "date"));
        Page<ServiceAppointmentInfoDTO> appointments  = serviceAppointmentInfoService.getAllAppointments(pageRequest);
        model.addAttribute("appointments", appointments);
        model.addAttribute("exception", exception);
        return "appointments/viewAllAppointments";
    }

    @GetMapping("/make/{vacationerID}/{serviceID}")
    public String makeAppointment(@PathVariable Long vacationerID,
                                  Model model, @PathVariable Long serviceID) {
        model.addAttribute("vacationer", vacationerService.getOne(vacationerID));
        model.addAttribute("service", serviceService.getOne(serviceID));
        return "vacationerAppointments/makeAppointment";
    }

    @PostMapping("/make")
    public String makeAppointment(@ModelAttribute("makeAppointmentForm")
                                      ServiceAppointmentInfoDTO serviceAppointmentInfoDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        serviceAppointmentInfoDTO.setCreatedBy(customUserDetails.getUsername());
        serviceAppointmentInfoService.makeAppointment(serviceAppointmentInfoDTO);
        return "redirect:/appointments";
    }

    @GetMapping("/cancel/{appointmentID}")
    public String cancelAppointment(@PathVariable Long appointmentID) {
        serviceAppointmentInfoService.cancelAppointment(appointmentID);
        return "redirect:/appointments";
    }

    @PostMapping("/filter")
    public String filterAppointments(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                    @ModelAttribute("appointmentFilterForm") StartAndEndDateFilterDTO filterDTO,
                                    Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "date"));
        model.addAttribute("appointments",
                serviceAppointmentInfoService.filterAppointmentsByDate(filterDTO, pageRequest));
        return "appointments/viewAllAppointments";
    }

    @PostMapping("/search")
    public String searchAppointments(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                    @ModelAttribute("appointmentsSearchForm") VacationerDTO vacationerDTO,
                                    Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "date"));
        model.addAttribute("appointments",
                serviceAppointmentInfoService.searchAppointments(vacationerDTO, pageRequest));
        return "appointments/viewAllAppointments";
    }
}
