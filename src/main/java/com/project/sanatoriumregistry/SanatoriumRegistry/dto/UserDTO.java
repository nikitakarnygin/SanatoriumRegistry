package com.project.sanatoriumregistry.SanatoriumRegistry.dto;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO extends PersonDTO {

    private String login;

    private String password;

    private String changePasswordToken;

    private RoleDTO role;

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = new RoleDTO(1L, "User", "test");
    }
}
