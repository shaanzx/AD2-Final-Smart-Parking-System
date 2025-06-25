package com.spms.vehicle.repository;

import com.spms.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    
    List<Vehicle> findByUserId(Long userId);
    
    List<Vehicle> findByVehicleType(Vehicle.VehicleType vehicleType);
    
    List<Vehicle> findByMake(String make);
    
    List<Vehicle> findByMakeAndModel(String make, String model);
    
    Optional<Vehicle> findByCurrentParkingSpaceId(Long parkingSpaceId);
    
    List<Vehicle> findByCurrentParkingSpaceIdIsNotNull();
    
    @Query("SELECT v FROM Vehicle v WHERE v.userId = :userId AND v.currentParkingSpaceId IS NOT NULL")
    List<Vehicle> findParkedVehiclesByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.currentParkingSpaceId IS NOT NULL")
    Long countParkedVehicles();
}