package com.spms.user.service;

import com.spms.user.dto.UserDTO;
import com.spms.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> getUserByUsername(String username);

    Optional<UserDTO> getUserByEmail(String email);

    List<UserDTO> getUsersByType(User.UserType userType);

    List<UserDTO> getActiveUsers();

    List<UserDTO> getActiveUsersByType(User.UserType userType);

    List<UserDTO> searchUsersByName(String name);

    UserDTO registerUser(UserDTO userDTO);

    UserDTO authenticateUser(String usernameOrEmail, String password);

    UserDTO updateUser(Long id, UserDTO userDetails);

    UserDTO changePassword(Long id, String currentPassword, String newPassword);

    UserDTO deactivateUser(Long id);

    UserDTO activateUser(Long id);

    void deleteUser(Long id);

    Long getActiveUsersCount();

    Long getParkingOwnersCount();
}
