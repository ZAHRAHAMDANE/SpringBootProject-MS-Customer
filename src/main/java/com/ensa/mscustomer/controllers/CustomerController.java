package com.ensa.mscustomer.controllers;

import com.ensa.mscustomer.dto.CustomerRequestDto;
import com.ensa.mscustomer.dto.CustomerResponseDto;
import com.ensa.mscustomer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<CustomerResponseDto>> getCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CustomerResponseDto> save(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto = customerService.save(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.findById(id);
        return ResponseEntity.ok(customerResponseDto);
    }

    @GetMapping("name/{firstName}")
    public ResponseEntity<CustomerResponseDto> findByFirstName(@PathVariable String firstName) {
        CustomerResponseDto customerResponseDto = customerService.findByFirstName(firstName);
        return ResponseEntity.ok(customerResponseDto);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<CustomerResponseDto> update(@RequestBody() CustomerRequestDto customerRequestDto, @PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.update(customerRequestDto, id);
        return ResponseEntity.accepted().body(customerResponseDto);
    }
}
