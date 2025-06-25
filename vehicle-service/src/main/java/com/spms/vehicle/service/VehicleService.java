package com.spms.vehicle.service;

import com.spms.vehicle.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

    Optional<VehicleDTO> getVehicleById(Long id);

    Optional<VehicleDTO> getVehicleByLicensePlate(String licensePlate);

    List<VehicleDTO> getVehiclesByUserId(Long userId);

    List<VehicleDTO> getVehiclesByType(String vehicleType);

    List<VehicleDTO> getVehiclesByMake(String make);

    List<VehicleDTO> getVehiclesByMakeAndModel(String make, String model);

    List<VehicleDTO> getParkedVehicles();

    List<VehicleDTO> getParkedVehiclesByUserId(Long userId);

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);

    VehicleDTO simulateVehicleEntry(Long vehicleId, Long parkingSpaceId);

    VehicleDTO simulateVehicleExit(Long vehicleId);

    void deleteVehicle(Long id);

    Long getParkedVehiclesCount();
}
