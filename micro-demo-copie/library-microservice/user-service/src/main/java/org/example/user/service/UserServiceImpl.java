package org.example.user.service;

import org.example.user.dto.UserDTO;
import org.example.user.exception.BusinessException;
import org.example.user.exception.ResourceNotFoundException;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.example.user.util.UserMapper;
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
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("A user with this email already exists");
        }

        User user = UserMapper.toEntity(dto);
        user.setId(null);
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.findByEmail(dto.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new BusinessException("A user with this email already exists");
                });

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        return UserMapper.toDto(userRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(existing);
    }
}
