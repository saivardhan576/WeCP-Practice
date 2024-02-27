package com.logitrack.logisticmanagement.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logitrack.logisticmanagement.Entity.Cargo;
import com.logitrack.logisticmanagement.Entity.Customer;
import com.logitrack.logisticmanagement.Repository.CargoRepository;
import com.logitrack.logisticmanagement.Repository.CustomerRepository;
import com.logitrack.logisticmanagement.dto.CargoStatusResponse;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
        // save the customer to the database
    }

    public CargoStatusResponse viewCargoStatus(Long cargoId) {

        // return cargoRepository.findById(cargoId).map((cargo)-> new CargoStatusResponse(cargo.getId(),cargo.getStatus()))
        //     .orElse(null);

       

        Cargo cargo= cargoRepository.findById(cargoId).orElse(null);
        if(cargo!=null){
            return new CargoStatusResponse(cargo.getId(),cargo.getStatus());
        }
        else{
            return null;
        }
        
        // Find the cargo by its id
    }
}
