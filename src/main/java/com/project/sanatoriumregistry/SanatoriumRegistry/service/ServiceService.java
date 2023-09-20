package com.project.sanatoriumregistry.SanatoriumRegistry.service;


import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.mapper.ServiceMapper;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.Service;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService extends GenericService<Service, ServiceDTO> {

    protected ServiceService(ServiceRepository serviceRepository,
                             ServiceMapper serviceMapper) {
        super(serviceRepository, serviceMapper);
    }

    public Page<ServiceDTO> getAllServices(Pageable pageable) {
        Page<Service> servicesPaginated = repository.findAll(pageable);
        List<ServiceDTO> result = mapper.toDTOs(servicesPaginated.getContent());
        return new PageImpl<>(result, pageable, servicesPaginated.getTotalElements());
    }

    public Page<ServiceDTO> filterServicesByCategory(ServiceDTO serviceDTO,
                                                     Pageable pageRequest) {

        String category = serviceDTO.getCategory() != null
                ? serviceDTO.getCategory().name()
                : null;

        Page<Service> servicesPaginated = ((ServiceRepository) repository)
                .filterServicesByCategory(
                        category,
                        pageRequest
                );
        List<ServiceDTO> result = mapper.toDTOs(servicesPaginated.getContent());
        return new PageImpl<>(result, pageRequest, servicesPaginated.getTotalElements());
    }

    public Page<ServiceDTO> findServiceByTitle(String title,
                                               Pageable pageRequest) {

        Page<Service> servicesPaginated = ((ServiceRepository) repository)
                .filterServiceByTitle(
                        title,
                        pageRequest
                );
        List<ServiceDTO> result = mapper.toDTOs(servicesPaginated.getContent());
        return new PageImpl<>(result, pageRequest, servicesPaginated.getTotalElements());
    }

    public ServiceDTO getServiceByTitle(final String title) {
        return mapper.toDTO(((ServiceRepository) repository).findServiceByTitle(title));
    }

    public ServiceDTO create(final ServiceDTO newService) {
        newService.setCreatedWhen(LocalDateTime.now());
        newService.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDTO(repository.save(mapper.toEntity(newService)));
    }
}
