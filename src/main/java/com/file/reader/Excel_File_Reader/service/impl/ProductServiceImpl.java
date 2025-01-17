package com.file.reader.Excel_File_Reader.service.impl;

import com.file.reader.Excel_File_Reader.entity.Product;
import com.file.reader.Excel_File_Reader.helper.ProductExcelHelper;
import com.file.reader.Excel_File_Reader.repository.ProductRepository;
import com.file.reader.Excel_File_Reader.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void save(MultipartFile file) throws IOException {

        if (!ProductExcelHelper.checkExcelFormat(file)) {
            throw new IllegalArgumentException("Please upload a valid Excel file.");
        }

        // Convert the Excel file to a list of products
        List<Product> productList = ProductExcelHelper.convertExcelToListOfProduct(file.getInputStream());

        // Save products to the database
        productRepository.saveAll(productList);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
