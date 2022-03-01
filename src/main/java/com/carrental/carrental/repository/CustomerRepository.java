package com.carrental.carrental.repository;

import com.carrental.carrental.models.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByIsMember(Boolean isMember, Sort sort);

    Customer findByUser_id(Long id);
}
