package com.ensa.mscustomer.services;

import com.ensa.mscustomer.dto.CustomerRequestDto;
import com.ensa.mscustomer.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    CustomerResponseDto save(CustomerRequestDto customerRequestDto);
    CustomerResponseDto findById(Long id);
    CustomerResponseDto findByFirstName(String firstName);
    void delete(Long id);
    CustomerResponseDto update(CustomerRequestDto customerRequestDto, Long id);
    List<CustomerResponseDto> findAll();
}
