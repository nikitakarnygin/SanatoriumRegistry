package com.project.sanatoriumregistry.SanatoriumRegistry.repository;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.ServiceAppointmentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ServiceAppointmentInfoRepository extends GenericRepository<ServiceAppointmentInfo> {

    @Query(nativeQuery = true,
            value = """
                select a.*
                from service_appointment_info a
                where a.date > DATE (:startDate) AND a.date < DATE (:endDate)
            """)
    Page<ServiceAppointmentInfo> filterAppointments(LocalDateTime startDate,
                                                    LocalDateTime endDate,
                                                    Pageable pageRequest);



    @Query(nativeQuery = true,
        value = """
            select a.* 
            from service_appointment_info a
                inner join vacationers v on v.id = a.vacationer_id
                             where v.first_name like '%' || coalesce(:firstName, '%') || '%'
                             and v.second_name like '%' || coalesce(:secondName, '%') || '%'
                             and v.phone like '%' || coalesce(:phone, '%') || '%'
        """)
    Page<ServiceAppointmentInfo> searchAppointments(String firstName,
                                                    String secondName,
                                                    String phone,
                                                    Pageable pageable);
}
