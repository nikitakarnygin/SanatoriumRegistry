package com.project.sanatoriumregistry.SanatoriumRegistry.repository;

import com.project.sanatoriumregistry.SanatoriumRegistry.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ServiceRepository extends GenericRepository<Service> {

    @Query(nativeQuery = true,
            value = """
                select s.*
                from services s
                where s.title = (:title)
            """)
    Page<Service> filterServiceByTitle(@RequestParam(value = "title") String title,
                                       Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                 select s.*
                 from services s
                 where s.category = (:category)
                  """)
    Page<Service> filterServicesByCategory(String category,
                                           Pageable pageable);

    Service findServiceByTitle(String title);
}
