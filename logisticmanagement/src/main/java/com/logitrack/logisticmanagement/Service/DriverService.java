package com.logitrack.logisticmanagement.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logitrack.logisticmanagement.Entity.Cargo;
import com.logitrack.logisticmanagement.Entity.Driver;
import com.logitrack.logisticmanagement.Repository.CargoRepository;
import com.logitrack.logisticmanagement.Repository.DriverRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
 
@Service
public class DriverService {
 
    @Autowired
    private DriverRepository driverRepository;
 
    @Autowired
    private CargoRepository cargoRepository;
 
    public Driver createDriver(Driver driver) {
        // add driver to database and return driver
        return driverRepository.save(driver);
    }
 
    public List<Driver> getAllDrivers() {
        // returning list of drivers from database
        return driverRepository.findAll();
 
    }
 
    public List<Cargo> viewDriverCargos(Long driverId) {
        // get assigned cargos of driver from database
        return cargoRepository.findByDriverId(driverId);
    }
 
    public boolean updateCargoStatus(Long cargoId, String newStatus) {
        // update cargo status in database
        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new EntityNotFoundException(cargoId + " not found!!"));
 
        cargo.setStatus(newStatus);
        cargoRepository.save(cargo);
        return true;
 
    }
 
}