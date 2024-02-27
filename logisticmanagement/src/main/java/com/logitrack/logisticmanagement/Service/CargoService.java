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
public class CargoService {
 
    @Autowired
    private CargoRepository cargoRepo;
 
    @Autowired
    private DriverRepository driverRepo;
     
    public Cargo addCargo(Cargo cargo) {
     
        return this.cargoRepo.save(cargo);
    }
 
    public List<Cargo> viewAllCargos() {
       // get all cargos from database
       return this.cargoRepo.findAll();
    }
 
    public boolean assignCargoToDriver(Long cargoId, Long driverId) {
        Cargo cargo = cargoRepo.findById(cargoId)
                .orElseThrow(() -> new EntityNotFoundException("Cargo not found with id: " + cargoId));

        Driver driver = driverRepo.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + driverId));

        cargo.setDriver(driver);
        cargoRepo.save(cargo);
        return true;
        //assign cargo to driver
    }
}