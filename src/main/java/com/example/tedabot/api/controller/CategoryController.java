package com.example.tedabot.api.controller;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.service.CategoryService;
import com.example.tedabot.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 11:16 *
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse<Category> response = categoryService.getOne(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Category category) {
        ApiResponse<?> response = categoryService.add(category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Category category, @PathVariable Long id) {
        ApiResponse<?> response = categoryService.edit(category, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse<?> response = categoryService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
