package com.spms.parkingspace.service.impl;

import com.spms.parkingspace.dto.ParkingSpaceDTO;
import com.spms.parkingspace.entity.ParkingSpace;
import com.spms.parkingspace.repository.ParkingSpaceRepository;
import com.spms.parkingspace.service.ParkingSpaceService;
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
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ParkingSpaceDTO convertToDTO(ParkingSpace entity) {
        return modelMapper.map(entity, ParkingSpaceDTO.class);
    }

    private ParkingSpace convertToEntity(ParkingSpaceDTO dto) {
        return modelMapper.map(dto, ParkingSpace.class);
    }

    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ParkingSpaceDTO> getParkingSpaceById(Long id) {
        return parkingSpaceRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<ParkingSpaceDTO> getParkingSpaceByNumber(String spaceNumber) {
        return parkingSpaceRepository.findBySpaceNumber(spaceNumber).map(this::convertToDTO);
    }

    public List<ParkingSpaceDTO> getAvailableParkingSpaces() {
        return parkingSpaceRepository.findByStatus(ParkingSpace.ParkingStatus.AVAILABLE)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ParkingSpaceDTO> getParkingSpacesByCity(String city) {
        return parkingSpaceRepository.findByCity(city)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ParkingSpaceDTO> getParkingSpacesByZone(String zone) {
        return parkingSpaceRepository.findByZone(zone)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ParkingSpaceDTO> getParkingSpacesByOwner(Long ownerId) {
        return parkingSpaceRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ParkingSpaceDTO> getAvailableSpacesByCityAndZone(String city, String zone) {
        return parkingSpaceRepository.findAvailableSpacesByCityAndZone(city, zone, ParkingSpace.ParkingStatus.AVAILABLE)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO dto) {
        if (parkingSpaceRepository.findBySpaceNumber(dto.getSpaceNumber()).isPresent()) {
            throw new RuntimeException("Parking space with number " + dto.getSpaceNumber() + " already exists");
        }
        ParkingSpace entity = convertToEntity(dto);
        return convertToDTO(parkingSpaceRepository.save(entity));
    }

    public ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO dto) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        parkingSpace.setLocation(dto.getLocation());
        parkingSpace.setZone(dto.getZone());
        parkingSpace.setCity(dto.getCity());
        parkingSpace.setHourlyRate(dto.getHourlyRate());

        return convertToDTO(parkingSpaceRepository.save(parkingSpace));
    }

    public ParkingSpaceDTO reserveParkingSpace(Long id, int durationHours) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        if (parkingSpace.getStatus() != ParkingSpace.ParkingStatus.AVAILABLE) {
            throw new RuntimeException("Parking space is not available for reservation");
        }

        parkingSpace.setStatus(ParkingSpace.ParkingStatus.RESERVED);
        parkingSpace.setReservedUntil(LocalDateTime.now().plusHours(durationHours));

        return convertToDTO(parkingSpaceRepository.save(parkingSpace));
    }

    public ParkingSpaceDTO releaseParkingSpace(Long id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        parkingSpace.setStatus(ParkingSpace.ParkingStatus.AVAILABLE);
        parkingSpace.setReservedUntil(null);

        return convertToDTO(parkingSpaceRepository.save(parkingSpace));
    }

    public ParkingSpaceDTO occupyParkingSpace(Long id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        if (parkingSpace.getStatus() == ParkingSpace.ParkingStatus.OCCUPIED) {
            throw new RuntimeException("Parking space is already occupied");
        }

        parkingSpace.setStatus(ParkingSpace.ParkingStatus.OCCUPIED);

        return convertToDTO(parkingSpaceRepository.save(parkingSpace));
    }

    public void deleteParkingSpace(Long id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found with id: " + id));

        parkingSpaceRepository.delete(parkingSpace);
    }

    public Long getAvailableSpacesCount(String city) {
        return parkingSpaceRepository.countByCityAndStatus(city, ParkingSpace.ParkingStatus.AVAILABLE);
    }
}