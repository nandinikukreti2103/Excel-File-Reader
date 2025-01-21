package com.file.reader.Excel_File_Reader.service.impl;

import com.file.reader.Excel_File_Reader.entity.Role;
import com.file.reader.Excel_File_Reader.entity.User;
import com.file.reader.Excel_File_Reader.exception.RoleNotFoundException;
import com.file.reader.Excel_File_Reader.exception.UserNotFoundException;
import com.file.reader.Excel_File_Reader.repository.RoleRepository;
import com.file.reader.Excel_File_Reader.repository.UserRepository;
import com.file.reader.Excel_File_Reader.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> getAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found with this id: " + userId));
    }

    @Override
    public User updateUser(Long userId, User userDetails){
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User does not exist with this id: " + userId));

        existingUser.setUserName(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setUserPassword(userDetails.getUserPassword());

        if(userDetails.getRole() != null){
            existingUser.setRole(userDetails.getRole());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId){
        User deletedUser = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found!"));

        userRepository.delete(deletedUser);
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found!"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new RoleNotFoundException("Role not found!"));

        user.setRole(role);
        return userRepository.save(user);
    }

}
