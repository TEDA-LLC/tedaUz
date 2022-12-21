package com.example.tedabot.controller;

import com.example.tedabot.model.Attachment;
import com.example.tedabot.service.ProductService;
import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.ProductDTO;
import com.example.tedabot.model.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:23 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse<List<Product>> response = productService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Product> response = productService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute ProductDTO productDTO) {
        ApiResponse<?> response = productService.save(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@ModelAttribute ProductDTO productDTO, @PathVariable Long id) {
        ApiResponse<?> response = productService.edit(productDTO, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse<?> response = productService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id){
        return productService.getPhoto(id);
    }
}
