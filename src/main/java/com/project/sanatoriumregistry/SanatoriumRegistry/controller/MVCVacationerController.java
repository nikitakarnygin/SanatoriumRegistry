package com.project.sanatoriumregistry.SanatoriumRegistry.controller;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.VacationerDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.VacationerService;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/vacationers")
public class MVCVacationerController {

    private final VacationerService vacationerService;

    public MVCVacationerController(VacationerService vacationerService) {
        this.vacationerService = vacationerService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "secondName"));
        Page<VacationerDTO> vacationers = vacationerService.getAllVacationers(pageRequest);
        model.addAttribute("vacationers", vacationers);
        model.addAttribute("exception", exception);
        return "vacationers/viewAllVacationers";
    }

    @PostMapping("/search")
    public String searchVacationers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int pageSize,
                              @ModelAttribute("vacationerSearchForm") VacationerDTO vacationerDTO,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.ASC, "second_name"));
        model.addAttribute("vacationers",
                vacationerService.searchVacationer(vacationerDTO, pageRequest));
        return "vacationers/viewAllVacationers";
    }

    @GetMapping("/card/{id}")
    public String userProfile(@PathVariable Integer id,
                              @ModelAttribute(value = "exception") String exception,
                              Model model) {
        model.addAttribute("vacationer", vacationerService.getOne(Long.valueOf(id)));
        model.addAttribute("exception", exception);
        return "vacationers/card";
    }

}
