package org.darrazi.ebank.repositories;

import org.darrazi.ebank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select c from Customer c where lower(c.name) like lower(concat('%',:name,'%'))")
    List<Customer> searchCustomer(@Param("name") String name);
}
