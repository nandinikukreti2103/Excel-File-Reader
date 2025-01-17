package com.file.reader.Excel_File_Reader.service;

import com.file.reader.Excel_File_Reader.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void save(MultipartFile file) throws IOException;

    List<Product> getAllProducts();
}
