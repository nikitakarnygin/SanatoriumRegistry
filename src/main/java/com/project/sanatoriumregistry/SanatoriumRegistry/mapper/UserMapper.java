package com.project.sanatoriumregistry.SanatoriumRegistry.mapper;

import com.project.sanatoriumregistry.SanatoriumRegistry.dto.UserDTO;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    protected UserMapper(ModelMapper modelMapper) {
        super(User.class, UserDTO.class, modelMapper);
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
    }

    @Override
    protected void setupMapper() {
    }
}
