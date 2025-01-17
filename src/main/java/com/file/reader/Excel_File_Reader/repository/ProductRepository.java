package com.file.reader.Excel_File_Reader.repository;

import com.file.reader.Excel_File_Reader.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
