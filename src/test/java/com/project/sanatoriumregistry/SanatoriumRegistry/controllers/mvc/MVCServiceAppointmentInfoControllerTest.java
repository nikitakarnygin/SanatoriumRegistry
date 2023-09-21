package com.project.sanatoriumregistry.SanatoriumRegistry.controllers.mvc;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.ServiceAppointmentInfoDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCServiceAppointmentInfoControllerTest extends MVCCommonTest {


    private final ServiceAppointmentInfoDTO serviceAppointmentInfoDTO =
            new ServiceAppointmentInfoDTO(LocalDateTime.now(), 1L, 1L);

    @Override
    @Test
    @DisplayName("Просмотр всех записей через MVC контроллер")
    @Order(0)
    @WithMockUser(username = "registry.user", roles = "REGISTRY", password = "user")
    protected void listAll() throws Exception {
        log.info("Тест просмотра записей MVC начат!");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/appointments")
                        .param("page", "1")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("appointments/viewAllAppointments"))
                .andExpect(model().attributeExists("appointments"))
                .andReturn();
    }

    @Override
    @Test
    @Order(1)
    @DisplayName("Создание записи через MVC контроллер")
    @WithMockUser(username = "registry.user", roles = "REGISTRY", password = "user")
    protected void createObject() throws Exception {
        log.info("Тест по созданию записи через MVC начат");
        mvc.perform(MockMvcRequestBuilders.get("/appointments/make/"
                                + serviceAppointmentInfoDTO.getVacationerID() + "/" +
                                serviceAppointmentInfoDTO.getServiceID())
                                .contentType(MediaType.APPLICATION_JSON)
                                .flashAttr("makeAppointmentForm", serviceAppointmentInfoDTO)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("vacationerAppointments/makeAppointment"))
                .andExpect(model().attributeExists("makeAppointmentForm"))
                .andReturn();
    }

    @Override
    protected void updateObject() throws Exception {
    }

    @Override
    protected void deleteObject() throws Exception {
    }
}
