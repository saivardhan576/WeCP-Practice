package com.logitrack.logisticmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.logitrack.logisticmanagement.Entity.Cargo;
import com.logitrack.logisticmanagement.Entity.Driver;
import com.logitrack.logisticmanagement.Service.BusinessService;
import com.logitrack.logisticmanagement.Service.CargoService;
import com.logitrack.logisticmanagement.Service.DriverService;

import java.util.List;
 
@RestController
@RequestMapping("/api/business")
public class BusinessController {
 
    @Autowired
    private CargoService cargoService;
    @Autowired
    private DriverService driverService;

    @Autowired
    private BusinessService businessService;

    @PostMapping("/driver")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public Driver createDriver(@RequestBody Driver driver) {
        // add driver to database and return driver
        return driverService.createDriver(driver);
    }
 
 
    @PostMapping("/cargo")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<Cargo> addCargo(@RequestBody Cargo cargo) {

        this.businessService.registerBusiness(cargo.getBusiness());
        this.driverService.createDriver(cargo.getDriver());

        // add cargo  and return the added cargo with status code 200
        return new ResponseEntity<Cargo>(this.cargoService.addCargo(cargo), HttpStatus.OK);
    }
 
    @GetMapping("/drivers")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        // return list of drivers
        return new ResponseEntity<List<Driver>>(this.driverService.getAllDrivers(), HttpStatus.OK);
    }
 
    @GetMapping("/cargo")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<List<Cargo>> viewAllCargos() {
        // return all cargos with status code 200
        return new ResponseEntity<List<Cargo>>(this.cargoService.viewAllCargos(), HttpStatus.OK);
    }

    @PostMapping("/assign-cargo/{cargoId}/{driverId}")
    @PreAuthorize("hasAuthority('BUSINESS')")
    public ResponseEntity<String> assignCargoToDriver(@PathVariable Long cargoId, @PathVariable Long driverId) {
        // assign cargo to a driver
        boolean assignmentSuccess = this.cargoService.assignCargoToDriver(cargoId, driverId);
        if (assignmentSuccess) {
            // if assignment is sucess return with this message
            return ResponseEntity.ok().body("{\"message\": \"Cargo assigned successfully.\"}");
        } else {
            // if assignemnt failed return with this message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign cargo.");
        }
    }
}