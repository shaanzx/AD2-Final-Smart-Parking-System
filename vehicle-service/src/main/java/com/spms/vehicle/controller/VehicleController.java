package com.spms.vehicle.controller;

import com.spms.vehicle.dto.VehicleDTO;
import com.spms.vehicle.service.impl.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleServiceImpl;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = vehicleServiceImpl.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license-plate/{licensePlate}")
    public ResponseEntity<VehicleDTO> getVehicleByLicensePlate(@PathVariable String licensePlate) {
        Optional<VehicleDTO> vehicle = vehicleServiceImpl.getVehicleByLicensePlate(licensePlate);
        return vehicle.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByUserId(@PathVariable Long userId) {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getVehiclesByUserId(userId);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/type/{vehicleType}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByType(@PathVariable String vehicleType) {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getVehiclesByType(vehicleType);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByMake(@PathVariable String make) {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getVehiclesByMake(make);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/make/{make}/model/{model}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByMakeAndModel(
            @PathVariable String make, @PathVariable String model) {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getVehiclesByMakeAndModel(make, model);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/parked")
    public ResponseEntity<List<VehicleDTO>> getParkedVehicles() {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getParkedVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/parked/user/{userId}")
    public ResponseEntity<List<VehicleDTO>> getParkedVehiclesByUserId(@PathVariable Long userId) {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getParkedVehiclesByUserId(userId);
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO createdVehicle = vehicleServiceImpl.createVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(
            @PathVariable Long id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO updatedVehicle = vehicleServiceImpl.updateVehicle(id, vehicleDTO);
            return ResponseEntity.ok(updatedVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/entry")
    public ResponseEntity<VehicleDTO> simulateVehicleEntry(
            @PathVariable Long id, @RequestBody Map<String, Long> request) {
        try {
            Long parkingSpaceId = request.get("parkingSpaceId");
            if (parkingSpaceId == null) {
                return ResponseEntity.badRequest().build();
            }
            VehicleDTO vehicle = vehicleServiceImpl.simulateVehicleEntry(id, parkingSpaceId);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/exit")
    public ResponseEntity<VehicleDTO> simulateVehicleExit(@PathVariable Long id) {
        try {
            VehicleDTO vehicle = vehicleServiceImpl.simulateVehicleExit(id);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleServiceImpl.deleteVehicle(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/stats/parked-count")
    public ResponseEntity<Map<String, Long>> getParkedVehiclesCount() {
        Long count = vehicleServiceImpl.getParkedVehiclesCount();
        return ResponseEntity.ok(Map.of("parkedVehicles", count));
    }
}
