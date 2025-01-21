package com.file.reader.Excel_File_Reader.service.impl;

import com.file.reader.Excel_File_Reader.entity.Role;
import com.file.reader.Excel_File_Reader.exception.RoleAlreadyExistException;
import com.file.reader.Excel_File_Reader.exception.RoleNotFoundException;
import com.file.reader.Excel_File_Reader.repository.RoleRepository;
import com.file.reader.Excel_File_Reader.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Role addRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RoleAlreadyExistException("Role already exists with this name: " + role.getName());
        }
        return roleRepository.save(role);
    }


    @Override
    public Role getRoleById(Long roleId){
        return roleRepository.findById(roleId)
                .orElseThrow(()->new RoleNotFoundException("Role not found with id: " + roleId));
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.findById(roleId)
                .ifPresentOrElse(roleRepository :: delete , ()-> {
                    throw new RoleNotFoundException("Role not Found!");
                } );
    }
}
