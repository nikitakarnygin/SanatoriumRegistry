package com.project.sanatoriumregistry.SanatoriumRegistry.controller;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.ServiceService;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.VacationerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@Controller
@RequestMapping("/services")
public class MVCServiceController {

    private final ServiceService serviceService;
    private final VacationerService vacationerService;

    protected MVCServiceController(ServiceService serviceService, VacationerService vacationerService) {
        this.serviceService = serviceService;
        this.vacationerService = vacationerService;
    }

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                      @ModelAttribute(name = "exception") final String exception,
                                      Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "category"));
        Page<ServiceDTO> services = serviceService.getAllServices(pageRequest);
        model.addAttribute("services", services);
        model.addAttribute("exception", exception);
        return "admin/services/viewAllServices";
    }

    @GetMapping("/{vacationerID}")
    public String getAllForVacationer(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         @ModelAttribute(name = "exception") final String exception,
                         Model model, @PathVariable Long vacationerID) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "category"));
        Page<ServiceDTO> services = serviceService.getAllServices(pageRequest);
        model.addAttribute("services", services);
        model.addAttribute("vacationer", vacationerService.getOne(vacationerID));
        model.addAttribute("exception", exception);
        return "services/viewAllServices";
    }

    @PostMapping("/{vacationerID}/filter")
    public String filterServicesByCategory(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                           @ModelAttribute("servicesFilterForm") ServiceDTO serviceDTO,
                                           Model model, @PathVariable Long vacationerID) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "title"));
        model.addAttribute("services",
                serviceService.filterServicesByCategory(serviceDTO, pageRequest));
        model.addAttribute("vacationer", vacationerService.getOne(vacationerID));
        return "services/viewAllServices";
    }

    @PostMapping("/filter")
    public String filterServicesByCategoryAdmin(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                           @ModelAttribute("servicesFilterFormAdmin") ServiceDTO serviceDTO,
                                           Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "title"));
        model.addAttribute("services",
                serviceService.filterServicesByCategory(serviceDTO, pageRequest));
        return "admin/services/viewAllServices";
    }

    @PostMapping("/{vacationerID}/search")
    public String searchServicesByTitle(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                       @ModelAttribute("servicesSearchForm") String title,
                                       Model model, @PathVariable Long vacationerID) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "title"));
        model.addAttribute("services",
                serviceService.findServiceByTitle(title, pageRequest));
        model.addAttribute("vacationer", vacationerService.getOne(vacationerID));
        return "services/viewAllServices";
    }

    @PostMapping("/search")
    public String searchServicesByTitleAdmin(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                        @ModelAttribute("servicesSearchFormAdmin") String title,
                                        Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Direction.DESC, "title"));
        model.addAttribute("services",
                serviceService.findServiceByTitle(title, pageRequest));
        return "admin/services/viewAllServices";
    }

    @GetMapping("/add")
    public String addServicePage(Model model) {
        model.addAttribute("serviceForm", new ServiceDTO());
        return "admin/services/add";
    }

    @PostMapping("/add")
    public String addService(@ModelAttribute("serviceForm") ServiceDTO serviceDTO,
                          BindingResult bindingResult) {
        if (serviceService.getServiceByTitle(serviceDTO.getTitle()) != null) {
            bindingResult.rejectValue("title", "error.title", "Такая услуга уже существует");
            return "admin/services/add";
        }
        serviceService.create(serviceDTO);
        return "redirect:/services";
    }

    @GetMapping("/update/{id}")
    public String updateService(@PathVariable Long id,
                                Model model) {
        model.addAttribute("serviceForm", serviceService.getOne(id));
        return "admin/services/update";
    }

    @PostMapping("/update")
    public String updateService(@ModelAttribute("serviceForm") ServiceDTO serviceDTO,
                                BindingResult bindingResult) {
        ServiceDTO serviceTitleDuplicated = serviceService.getServiceByTitle(serviceDTO.getTitle());
        ServiceDTO foundService = serviceService.getOne(serviceDTO.getId());
        if (serviceTitleDuplicated != null && Objects.equals(serviceTitleDuplicated.getTitle(), foundService.getTitle())) {
            bindingResult.rejectValue("title", "error.title", "Такая услуга уже существует");
            return "admin/services/update";
        }
        foundService.setTitle(serviceDTO.getTitle());
        foundService.setCategory(serviceDTO.getCategory());
        foundService.setDuration(serviceDTO.getDuration());
        foundService.setDescription(serviceDTO.getDescription());
        foundService.setCost(serviceDTO.getCost());
        serviceService.update(foundService);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        serviceService.deleteSoft(id);
        return "redirect:/services";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        serviceService.restore(id);
        return "redirect:/services";
    }
}
