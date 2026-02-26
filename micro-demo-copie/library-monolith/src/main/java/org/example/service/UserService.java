package org.example.service;

import org.example.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO create(UserDTO dto);

    UserDTO update(Long id, UserDTO dto);

    void delete(Long id);
}
