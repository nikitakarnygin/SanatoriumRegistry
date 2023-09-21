package com.project.sanatoriumregistry.SanatoriumRegistry.service.userdetails;

import com.project.sanatoriumregistry.SanatoriumRegistry.constants.UserRolesConstants;
import com.project.sanatoriumregistry.SanatoriumRegistry.model.User;
import com.project.sanatoriumregistry.SanatoriumRegistry.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService
        implements UserDetailsService {
    private final UserRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(null, username, adminPassword, false,
                    List.of(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.ADMIN)));
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.REGISTRY));


            return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), user.isDeleted(), authorities);
        }
    }
}

