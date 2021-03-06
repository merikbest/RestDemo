package com.gmail.merikbest2015.controller;

import com.gmail.merikbest2015.model.Customer;
import com.gmail.merikbest2015.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {
        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.getById(customerId);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        customerService.save(customer);

        return new ResponseEntity<>(customer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        customerService.save(customer);

        return new ResponseEntity<>(customer, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")Long id) {
        Customer customer = customerService.getById(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAll();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
