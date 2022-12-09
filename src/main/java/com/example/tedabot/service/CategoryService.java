package com.example.tedabot.service;


import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.model.Category;
import com.example.tedabot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * magneticuzbot *  * 15:59 *
 */

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ApiResponse<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return ApiResponse.<List<Category>>builder().
                message("Here").
                status(200).
                success(true).
                data(categories).
                build();
    }
}
