package com.example.tedabot.controller;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.model.Category;
import com.example.tedabot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * magneticuzbot *  * 15:58 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse<List<Category>> response = categoryService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
