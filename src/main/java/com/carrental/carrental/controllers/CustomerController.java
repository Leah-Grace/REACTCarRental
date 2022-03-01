package com.carrental.carrental.controllers;

import com.carrental.carrental.auth.User;
import com.carrental.carrental.models.Customer;
import com.carrental.carrental.models.Rental;
import com.carrental.carrental.models.Vehicle;
import com.carrental.carrental.repository.CustomerRepository;
import com.carrental.carrental.repository.RentalRepository;
import com.carrental.carrental.repository.UserRepository;
import com.carrental.carrental.repository.VehicleRepository;
import com.carrental.carrental.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    @GetMapping("/membership/{isMember}")
    public @ResponseBody ResponseEntity<List<Customer>> getAllMembers(@PathVariable Boolean isMember) {
        return new ResponseEntity<>(repository.findAllByIsMember(isMember, Sort.by("name")), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Customer> postNewCustomer(@RequestBody Customer newCustomer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = userRepository.findById(userDetails.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        newCustomer.setUser(currentUser);

        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<Customer> createNewRental(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle rentedVehicle = vehicleRepository.findById(vehicle.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Rental newRental = new Rental(rentedVehicle, customer);

        rentalRepository.save(newRental);

        rentedVehicle.getRentals().add(newRental);
        customer.getRentals().add(newRental);

        vehicleRepository.save(vehicle);

        return new ResponseEntity<>(repository.save(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer update) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (update.getStores() != null) {
            customer.getStores().addAll(update.getStores());
        }
        if (update.getName() != null) {
            customer.setName(update.getName());
        }
        if (update.getLicenseNumber() != null) {
            customer.setLicenseNumber(update.getLicenseNumber());
        }
        if (update.getIsMember() != null) {
            customer.setIsMember(update.getIsMember());
        }

        return new ResponseEntity<>(repository.save(customer), HttpStatus.ACCEPTED);
    }

    @PutMapping("/stores/{id}")
    public Customer updateStoreById(@PathVariable Long id, @RequestBody Customer updates) {
        Customer selectedCustomer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getStores() != null) {
            selectedCustomer.getStores().addAll(updates.getStores());
        }

        return repository.save(selectedCustomer);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
