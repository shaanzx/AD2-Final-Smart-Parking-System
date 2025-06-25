package com.spms.parkingspace.controller;

import com.spms.parkingspace.dto.ParkingSpaceDTO;
import com.spms.parkingspace.service.impl.ParkingSpaceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceServiceImpl parkingSpaceServiceImpl;

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getAllParkingSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceById(@PathVariable Long id) {
        Optional<ParkingSpaceDTO> space = parkingSpaceServiceImpl.getParkingSpaceById(id);
        return space.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/space-number/{spaceNumber}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceByNumber(@PathVariable String spaceNumber) {
        Optional<ParkingSpaceDTO> space = parkingSpaceServiceImpl.getParkingSpaceByNumber(spaceNumber);
        return space.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getAvailableParkingSpaces());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByCity(@PathVariable String city) {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getParkingSpacesByCity(city));
    }

    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByZone(@PathVariable String zone) {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getParkingSpacesByZone(zone));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getParkingSpacesByOwner(ownerId));
    }

    @GetMapping("/available/city/{city}/zone/{zone}")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableSpacesByCityAndZone(
            @PathVariable String city, @PathVariable String zone) {
        return ResponseEntity.ok(parkingSpaceServiceImpl.getAvailableSpacesByCityAndZone(city, zone));
    }

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createParkingSpace(@Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(parkingSpaceServiceImpl.createParkingSpace(parkingSpaceDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(
            @PathVariable Long id, @Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        try {
            return ResponseEntity.ok(parkingSpaceServiceImpl.updateParkingSpace(id, parkingSpaceDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<ParkingSpaceDTO> reserveParkingSpace(
            @PathVariable Long id, @RequestBody Map<String, Integer> request) {
        try {
            int durationHours = request.getOrDefault("durationHours", 1);
            return ResponseEntity.ok(parkingSpaceServiceImpl.reserveParkingSpace(id, durationHours));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<ParkingSpaceDTO> releaseParkingSpace(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(parkingSpaceServiceImpl.releaseParkingSpace(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/occupy")
    public ResponseEntity<ParkingSpaceDTO> occupyParkingSpace(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(parkingSpaceServiceImpl.occupyParkingSpace(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Long id) {
        try {
            parkingSpaceServiceImpl.deleteParkingSpace(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stats/available-count/city/{city}")
    public ResponseEntity<Map<String, Long>> getAvailableSpacesCount(@PathVariable String city) {
        Long count = parkingSpaceServiceImpl.getAvailableSpacesCount(city);
        return ResponseEntity.ok(Map.of("availableSpaces", count));
    }
}
