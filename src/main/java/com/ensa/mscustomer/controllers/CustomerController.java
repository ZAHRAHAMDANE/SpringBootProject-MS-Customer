package com.ensa.mscustomer.controllers;

import com.ensa.mscustomer.dto.CustomerRequestDto;
import com.ensa.mscustomer.dto.CustomerResponseDto;
import com.ensa.mscustomer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public List<CustomerResponseDto> getCustomer() {
        return customerService.findAll();
    }

    @PostMapping("")
    public CustomerResponseDto save(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.save(customerRequestDto);
    }
}
