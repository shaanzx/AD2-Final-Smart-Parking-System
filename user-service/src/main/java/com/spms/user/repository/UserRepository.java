package com.spms.user.repository;

import com.spms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    List<User> findByUserType(User.UserType userType);
    
    List<User> findByIsActive(Boolean isActive);
    
    List<User> findByUserTypeAndIsActive(User.UserType userType, Boolean isActive);
    
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.userType = :userType AND u.isActive = true")
    Long countActiveUsersByType(@Param("userType") User.UserType userType);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}