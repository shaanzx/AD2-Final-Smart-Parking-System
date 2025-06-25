package com.spms.parkingspace.repository;

import com.spms.parkingspace.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    
    List<ParkingSpace> findByStatus(ParkingSpace.ParkingStatus status);
    
    List<ParkingSpace> findByCity(String city);
    
    List<ParkingSpace> findByZone(String zone);
    
    List<ParkingSpace> findByOwnerId(Long ownerId);
    
    List<ParkingSpace> findByCityAndZone(String city, String zone);
    
    List<ParkingSpace> findByCityAndStatus(String city, ParkingSpace.ParkingStatus status);
    
    Optional<ParkingSpace> findBySpaceNumber(String spaceNumber);
    
    @Query("SELECT p FROM ParkingSpace p WHERE p.city = :city AND p.zone = :zone AND p.status = :status")
    List<ParkingSpace> findAvailableSpacesByCityAndZone(@Param("city") String city, 
                                                        @Param("zone") String zone, 
                                                        @Param("status") ParkingSpace.ParkingStatus status);
    
    @Query("SELECT COUNT(p) FROM ParkingSpace p WHERE p.city = :city AND p.status = :status")
    Long countByCityAndStatus(@Param("city") String city, @Param("status") ParkingSpace.ParkingStatus status);
}