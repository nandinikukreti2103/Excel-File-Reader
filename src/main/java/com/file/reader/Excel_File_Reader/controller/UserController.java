package com.file.reader.Excel_File_Reader.controller;

import com.file.reader.Excel_File_Reader.entity.User;
import com.file.reader.Excel_File_Reader.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, User userDetails){
        User updateduser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updateduser);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/{roleId}")
    public ResponseEntity<User> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        User assignedRole = userService.assignRoleToUser(userId,roleId);
        return ResponseEntity.ok(assignedRole);
    }
}
