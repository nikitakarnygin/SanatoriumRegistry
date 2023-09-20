package com.project.sanatoriumregistry.SanatoriumRegistry.repository;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.BookingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingInfoRepository extends GenericRepository<BookingInfo> {

    @Query(nativeQuery = true,
            value = """
                select b.*
                from booking_info b
                where b.start_date > DATE (:startDate) AND b.start_date < DATE (:endDate)
            """)
    Page<BookingInfo> filterBookings(LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     Pageable pageRequest);

    @Query(nativeQuery = true,
            value = """
            select b.* 
            from booking_info b
                inner join vacationers v on v.id = b.vacationer_id
                             where v.first_name like '%' || coalesce(:firstName, '%') || '%'
                             and v.second_name like '%' || coalesce(:secondName, '%') || '%'
                             and v.phone like '%' || coalesce(:phone, '%') || '%'
        """)
    Page<BookingInfo> searchBookings(String firstName,
                                                    String secondName,
                                                    String phone,
                                                    Pageable pageable);
}
