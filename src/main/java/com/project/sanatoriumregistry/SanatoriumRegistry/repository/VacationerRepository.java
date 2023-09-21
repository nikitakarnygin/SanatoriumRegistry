package com.project.sanatoriumregistry.SanatoriumRegistry.repository;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.Vacationer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationerRepository extends GenericRepository<Vacationer> {

    @Query(nativeQuery = true,
            value = """
                 select v.*
                 from vacationers v
                 where v.first_name like '%' || coalesce(:firstName, '%') || '%'
                 and v.second_name like '%' || coalesce(:secondName, '%') || '%'
                 and v.phone like '%' || coalesce(:phone, '%') || '%'
                  """)
    Page<Vacationer> searchVacationers(String firstName,
                                 String secondName,
                                 String phone,
                                 Pageable pageable);
}
