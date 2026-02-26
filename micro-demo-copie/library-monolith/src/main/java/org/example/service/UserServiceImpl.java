package org.example.service;

import org.example.dto.UserDTO;
import org.example.exception.InvalidBookException;
import org.example.exception.ResourceNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.util.DTOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(DTOMapper::toUserDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return DTOMapper.toUserDTO(user);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        validateUser(dto);
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new InvalidBookException("A user with this email already exists");
        }

        User user = DTOMapper.toUser(dto);
        user.setId(null);
        return DTOMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        validateUser(dto);
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.findByEmail(dto.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new InvalidBookException("A user with this email already exists");
                });

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        return DTOMapper.toUserDTO(userRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    private void validateUser(UserDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidBookException("Name is required");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidBookException("Email is required");
        }
    }
}
