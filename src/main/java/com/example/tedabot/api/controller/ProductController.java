package com.example.tedabot.api.controller;

import com.example.tedabot.api.service.ProductService;
import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.dto.ProductDTO;
import com.example.tedabot.api.service.UserService;
import com.example.tedabot.model.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:23 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<List<Product>> response = productService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id, HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<Product> response = productService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute ProductDTO productDTO, HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<?> response = productService.save(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@ModelAttribute ProductDTO productDTO, @PathVariable Long id, HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<?> response = productService.edit(productDTO, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<?> response = productService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
