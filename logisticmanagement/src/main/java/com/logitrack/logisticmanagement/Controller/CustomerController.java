package com.logitrack.logisticmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.logisticmanagement.Service.CustomerService;
import com.logitrack.logisticmanagement.dto.CargoStatusResponse;

@RestController
@RequestMapping
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customer/cargo-status/{cargoId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<CargoStatusResponse> viewCargoStatus(@PathVariable Long cargoId) {
        if(cargoId!=null){
        return new ResponseEntity<CargoStatusResponse>(customerService.viewCargoStatus(cargoId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<CargoStatusResponse>(HttpStatus.NOT_FOUND);
        }

        // get cargo status from customer service and return it with status code 200

        // if cargo status is not found, return 404 status code
    }

}

