package com.file.reader.Excel_File_Reader.controller;

import com.file.reader.Excel_File_Reader.entity.Role;
import com.file.reader.Excel_File_Reader.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> addRole(Role role){
        Role roleAdded = roleService.addRole(role);
        return ResponseEntity.ok(roleAdded);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        Role role = roleService.getRoleById(roleId);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        List<Role> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Void> delete(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
