package com.file.reader.Excel_File_Reader.repository;

import com.file.reader.Excel_File_Reader.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
}
