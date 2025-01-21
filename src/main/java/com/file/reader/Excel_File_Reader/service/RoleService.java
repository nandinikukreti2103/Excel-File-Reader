package com.file.reader.Excel_File_Reader.service;

import com.file.reader.Excel_File_Reader.entity.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);

    Role getRoleById(Long roleId);

    List<Role> getAllRoles();

    void deleteRole(Long roleId);
}
