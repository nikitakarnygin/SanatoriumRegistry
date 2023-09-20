package com.project.sanatoriumregistry.SanatoriumRegistry.constants;

import java.util.List;

public interface SecurityConstants {

    List<String> RESOURCES_WHITE_LIST = List.of(
            "/resources/**",
            "/static/**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/",
            "/error",
            "/swagger-ui/**",
            "/webjars/bootstrap/5.3.0/**",
            "/webjars/bootstrap/5.3.0/css/**",
            "/webjars/bootstrap/5.3.0/js/**",
            "/v3/api-docs/**");

    List<String> VACATIONERS_PERMISSION_LIST = List.of(
            "/vacationers/",
            "/vacationers/search",
            "/vacationers/card/{id}"
    );

    List<String> SERVICES_PERMISSION_LIST = List.of(
            "/services/{vacationerID}",
            "/services/{vacationerID}/filter",
            "/services/{vacationerID}/search"
    );

    List<String> APPOINTMENTS_PERMISSION_LIST = List.of(
            "/appointments",
            "/appointments/make/{vacationerID}/{serviceID}",
            "/appointments/cancel/{appointmentID}",
            "/appointments/filter",
            "/appointments/search"
    );

    List<String> BOOKINGS_PERMISSION_LIST = List.of(
            "/bookings",
            "/bookings/filter",
            "/bookings/search",
            "/bookings/settle-vacationer/{bookingID}",
            "/bookings/unsettle-vacationer/{bookingID}"
    );

    List<String> USERS_WHITE_LIST = List.of(
            "/login");

    List<String> SERVICES_ADMIN_LIST = List.of(
            "/services",
            "/services/add",
            "/services/filter",
            "/services/search"
    );

    List<String> USERS_ADMIN_LIST = List.of(
            "/users",
            "/users/add",
            "/users/profile/{id}",
            "/users/profile/update/{id}",
            "/users/profile/change-password/{id}",
            "/users/delete/{id}",
            "/users/restore/{id}",
            "/users/search"
    );
}
