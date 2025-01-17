package com.file.reader.Excel_File_Reader.controller;

import com.file.reader.Excel_File_Reader.entity.Product;
import com.file.reader.Excel_File_Reader.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile files) throws IOException {
        productService.save(files);
        return ResponseEntity.ok(Map.of("message:","File is uploaded and data is saved in db."));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
}
