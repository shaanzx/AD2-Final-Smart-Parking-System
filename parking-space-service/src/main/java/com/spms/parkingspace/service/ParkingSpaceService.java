package com.spms.parkingspace.service;

import com.spms.parkingspace.dto.ParkingSpaceDTO;

import java.util.List;
import java.util.Optional;

public interface ParkingSpaceService {
    List<ParkingSpaceDTO> getAllParkingSpaces();
    Optional<ParkingSpaceDTO> getParkingSpaceById(Long id);
    Optional<ParkingSpaceDTO> getParkingSpaceByNumber(String spaceNumber);
    List<ParkingSpaceDTO> getAvailableParkingSpaces();
    List<ParkingSpaceDTO> getParkingSpacesByCity(String city);
    List<ParkingSpaceDTO> getParkingSpacesByZone(String zone);
    List<ParkingSpaceDTO> getParkingSpacesByOwner(Long ownerId);
    List<ParkingSpaceDTO> getAvailableSpacesByCityAndZone(String city, String zone);
    ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO dto);
    ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO dto);
    ParkingSpaceDTO reserveParkingSpace(Long id, int durationHours);
    ParkingSpaceDTO releaseParkingSpace(Long id);
    ParkingSpaceDTO occupyParkingSpace(Long id);
    void deleteParkingSpace(Long id);
    Long getAvailableSpacesCount(String city);
}