package com.logitrack.logisticmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logitrack.logisticmanagement.Entity.Business;
import com.logitrack.logisticmanagement.Repository.BusinessRepository;
 
@Service
public class BusinessService {
 
    @Autowired
    private BusinessRepository businessRepository;
 
    public Business registerBusiness(Business business) {
        // save business to database
        return this.businessRepository.save(business);
    }
}