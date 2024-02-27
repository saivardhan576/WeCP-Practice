package com.logitrack.logisticmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logitrack.logisticmanagement.Entity.Business;
 
@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{
    // extend jpa repository and add custom methods if needed
}
