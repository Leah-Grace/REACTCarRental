package com.carrental.carrental.controllers;

import com.carrental.carrental.models.Location;
import com.carrental.carrental.models.Vehicle;
import com.carrental.carrental.repository.LocationRepository;
import com.carrental.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    VehicleRepository repository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    public @ResponseBody ResponseEntity<List<Vehicle>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Vehicle> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public @ResponseBody ResponseEntity<List<Vehicle>> getAllByMake(@PathVariable String make) {
        return new ResponseEntity<>(repository.findAllByMake(make, Sort.by("model")), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Vehicle> postVehicle(@RequestBody Vehicle newVehicle) {
        return new ResponseEntity<>(repository.save(newVehicle), HttpStatus.CREATED);
    }

    @PostMapping("/location")
    public ResponseEntity<Vehicle> addLocation(@RequestBody Vehicle update) {
        Vehicle selVeh = repository.findById(update.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Location newLocation = locationRepository.save(update.getLocation());


        selVeh.setLocation(newLocation);
        return new ResponseEntity<>(repository.save(selVeh), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Vehicle> updateVehicleById(@PathVariable Long id, @RequestBody Vehicle update) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (update.getMake() != null) {
            vehicle.setMake(update.getMake());
        }
        if (update.getModel() != null) {
            vehicle.setModel(update.getModel());
        }
        if (update.getYear() != null) {
            vehicle.setYear(update.getYear());
        }
        if (update.getMiles() != null) {
            vehicle.setMiles(update.getMiles());
        }
        if (update.getLocation() != null) {

            locationRepository.save(update.getLocation());

            vehicle.setLocation(update.getLocation());
        }

        return new ResponseEntity<>(repository.save(vehicle), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteVehicleById(@PathVariable Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping("/location/{id}")
    public ResponseEntity<String> deleteLocationByVehicle(@PathVariable Long id) {
        Vehicle selVeh = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (selVeh.getLocation() != null) {
            selVeh.setLocation(null);
        }

        return new ResponseEntity<>("Location Deleted", HttpStatus.OK);
    }
}
