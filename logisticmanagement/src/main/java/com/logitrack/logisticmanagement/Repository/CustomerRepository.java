package com.logitrack.logisticmanagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logitrack.logisticmanagement.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    // extends JpaRepository and add custom methods if needed
}