package com.logitrack.logisticmanagement.Repository;


 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logitrack.logisticmanagement.Entity.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    // extend jpa repository and add custom methods if needed
}
