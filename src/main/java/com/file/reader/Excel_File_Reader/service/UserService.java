package com.file.reader.Excel_File_Reader.service;

import com.file.reader.Excel_File_Reader.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getUserById(Long userId);

    User updateUser(Long userId, User userDetails);

    void deleteUser(Long userId);

    User assignRoleToUser(Long userId, Long roleId);
}
