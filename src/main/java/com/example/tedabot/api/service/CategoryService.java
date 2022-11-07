package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.model.Category;
import com.example.tedabot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 11:17 *
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ApiResponse<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ApiResponse.<List<Category>>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<List<Category>>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(categories).
                    build();
        }
    }

    public ApiResponse<Category> getOne(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ApiResponse.<Category>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<Category>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(optionalCategory.get()).
                    build();
        }
    }


    public ApiResponse<?> add(Category category) {
        if (category.getNameUz().isEmpty() || category.getNameRu().isEmpty()) {
            return ApiResponse.builder().
                    message("Name is null").
                    status(204).
                    success(false).
                    build();
        }
        categoryRepository.save(category);
        return ApiResponse.builder().
                message("Saved").
                status(200).
                success(true).
                build();
    }

    public ApiResponse<?> edit(Category category, Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ApiResponse.builder().
                    message("Category Not Found").
                    status(204).
                    success(false).
                    build();
        }
        if (category.getNameUz().isEmpty() || category.getNameRu().isEmpty()) {
            return ApiResponse.builder().
                    message("Name is null").
                    status(204).
                    success(false).
                    build();
        }
        Category categoryOld = optionalCategory.get();
        categoryOld.setNameUz(category.getNameUz());
        categoryOld.setNameRu(category.getNameRu());
        categoryRepository.save(categoryOld);

        return ApiResponse.builder().
                message("Edited").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<?> delete(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return ApiResponse.builder().
                    message("Category Not Found").
                    status(204).
                    success(false).
                    build();
        }
        categoryRepository.deleteById(id);
        return ApiResponse.builder().
                message("Deleted").
                status(201).
                success(true).
                build();
    }
}
