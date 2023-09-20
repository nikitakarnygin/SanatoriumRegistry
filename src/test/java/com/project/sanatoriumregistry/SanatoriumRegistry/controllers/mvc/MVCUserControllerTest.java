package com.project.sanatoriumregistry.SanatoriumRegistry.controllers.mvc;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.UserDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@Rollback(value = false)
public class MVCUserControllerTest extends MVCCommonTest {

    @Autowired
    private UserService userService;
    private final UserDTO userDTO = new UserDTO ("testLogin", "test");

    @Override
    @Test
    @DisplayName("Просмотр всех пользователей через MVC контроллер")
    @Order(0)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void listAll() throws Exception {
        log.info("Тест просмотра пользователей MVC начат!");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/users")
                        .param("page", "1")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewAllUsers"))
                .andExpect(model().attributeExists("users"))
                .andReturn();
    }

    @Order(1)
    @Test
    @DisplayName("Софт удаление пользователя через MVC контроллер, тестирование 'users/delete'")
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    @Override
    protected void deleteObject() throws Exception {
        UserDTO userDTODeletion = userService.getUserByLogin(userDTO.getLogin());
        userDTODeletion.setIsDeleted(true);
        mvc.perform(get("/users/delete/{id}", userDTODeletion.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authors"))
                .andExpect(redirectedUrl("/authors"));

        UserDTO deletedUser = userService.getOne(userDTODeletion.getId());
        assertTrue(deletedUser.getIsDeleted());
        log.info("Тест по soft удалению автора через MVC закончен успешно!");
    }


    @Override
    protected void createObject() throws Exception {

    }

    @Override
    protected void updateObject() throws Exception {

    }
}
