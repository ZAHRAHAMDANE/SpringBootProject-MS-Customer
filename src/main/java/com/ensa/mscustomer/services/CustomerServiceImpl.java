package com.ensa.mscustomer.services;

import com.ensa.mscustomer.dao.CustomerRepository;
import com.ensa.mscustomer.dto.CustomerRequestDto;
import com.ensa.mscustomer.dto.CustomerResponseDto;
import com.ensa.mscustomer.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        Customer saved = customerRepository.save(customer);
        return modelMapper.map(saved, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto findByFirstName(String firstName) {
        Customer customer = customerRepository.findByFirstName(firstName);
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequestDto, Long id) throws EntityNotFoundException {
        Optional<Customer> clientEntityOptional = customerRepository.findById(id);
        if (clientEntityOptional.isPresent()) {
            Customer customer = modelMapper.map(customerRequestDto, Customer.class);
            customer.setId(id);
            Customer updated = customerRepository.save(customer);
            return modelMapper.map(updated, CustomerResponseDto.class);
        } else {
            throw new EntityNotFoundException("Customer not found !");
        }
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll()
                .stream().map(el -> modelMapper.map(el, CustomerResponseDto.class))
                .collect(Collectors.toList());
    }
}
