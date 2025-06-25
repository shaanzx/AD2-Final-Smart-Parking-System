package com.spms.vehicle.service.impl;

import com.spms.vehicle.dto.VehicleDTO;
import com.spms.vehicle.entity.Vehicle;
import com.spms.vehicle.repository.VehicleRepository;
import com.spms.vehicle.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VehicleDTO> getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class));
    }

    @Override
    public Optional<VehicleDTO> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate)
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class));
    }

    @Override
    public List<VehicleDTO> getVehiclesByUserId(Long userId) {
        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getVehiclesByType(String vehicleTypeStr) {
        Vehicle.VehicleType vehicleType;
        try {
            vehicleType = Vehicle.VehicleType.valueOf(vehicleTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid vehicle type: " + vehicleTypeStr);
        }
        List<Vehicle> vehicles = vehicleRepository.findByVehicleType(vehicleType);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getVehiclesByMake(String make) {
        List<Vehicle> vehicles = vehicleRepository.findByMake(make);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getVehiclesByMakeAndModel(String make, String model) {
        List<Vehicle> vehicles = vehicleRepository.findByMakeAndModel(make, model);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getParkedVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByCurrentParkingSpaceIdIsNotNull();
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getParkedVehiclesByUserId(Long userId) {
        List<Vehicle> vehicles = vehicleRepository.findParkedVehiclesByUserId(userId);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate()).isPresent()) {
            throw new RuntimeException("Vehicle with license plate " + vehicleDTO.getLicensePlate() + " already exists");
        }
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        if (!vehicle.getLicensePlate().equals(vehicleDTO.getLicensePlate())) {
            if (vehicleRepository.findByLicensePlate(vehicleDTO.getLicensePlate()).isPresent()) {
                throw new RuntimeException("Vehicle with license plate " + vehicleDTO.getLicensePlate() + " already exists");
            }
        }

        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setColor(vehicleDTO.getColor());

        // Handle VehicleType enum mapping carefully:
        try {
            vehicle.setVehicleType(Vehicle.VehicleType.valueOf(String.valueOf(vehicleDTO.getVehicleType())));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid vehicle type: " + vehicleDTO.getVehicleType());
        }

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(updatedVehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO simulateVehicleEntry(Long vehicleId, Long parkingSpaceId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + vehicleId));

        if (vehicle.getCurrentParkingSpaceId() != null) {
            throw new RuntimeException("Vehicle is already parked");
        }

        vehicle.setCurrentParkingSpaceId(parkingSpaceId);
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setExitTime(null);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO simulateVehicleExit(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + vehicleId));

        if (vehicle.getCurrentParkingSpaceId() == null) {
            throw new RuntimeException("Vehicle is not currently parked");
        }

        vehicle.setCurrentParkingSpaceId(null);
        vehicle.setExitTime(LocalDateTime.now());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(savedVehicle, VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        if (vehicle.getCurrentParkingSpaceId() != null) {
            throw new RuntimeException("Cannot delete vehicle that is currently parked. Please exit the vehicle first.");
        }

        vehicleRepository.delete(vehicle);
    }

    @Override
    public Long getParkedVehiclesCount() {
        return vehicleRepository.countParkedVehicles();
    }
}
