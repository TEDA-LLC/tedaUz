package com.example.tedabot.service;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.ProductDTO;
import com.example.tedabot.model.Attachment;
import com.example.tedabot.model.Category;
import com.example.tedabot.model.Product;
import com.example.tedabot.repository.CategoryRepository;
import com.example.tedabot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:24 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ApiResponse<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ApiResponse.<List<Product>>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<List<Product>>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(products).
                    build();
        }
    }

    public ApiResponse<Product> getOne(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.<Product>builder().
                    message("Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            return ApiResponse.<Product>builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(optionalProduct.get()).
                    build();
        }
    }

    @SneakyThrows
    public ApiResponse<?> save(ProductDTO productDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (categoryOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Category not found").
                    status(204).
                    success(false).
                    build();
        }

        MultipartFile photo = productDTO.getPhoto();
        Attachment attachment = new Attachment();
        attachment.setBytes(photo.getBytes());
        attachment.setOriginalName(photo.getOriginalFilename());

        Product product = new Product();
        product.setCategory(categoryOptional.get());
        product.setAttachment(attachment);
        product.setPrice(productDTO.getPrice());
        product.setNameRu(productDTO.getNameRu());
        product.setNameUz(productDTO.getNameUz());
        product.setNameEn(productDTO.getNameEn());
        product.setDescriptionRu(productDTO.getDescriptionRu());
        product.setDescriptionUz(productDTO.getDescriptionUz());
        product.setDescriptionEn(productDTO.getDescriptionEn());

        productRepository.save(product);

        return ApiResponse.builder().
                message("Saved").
                status(200).
                success(true).
                build();
    }

    @SneakyThrows
    public ApiResponse<?> edit(ProductDTO productDTO, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().
                    message("Product Not Found").
                    status(204).
                    success(false).
                    build();
        }
        if (optionalCategory.isEmpty()) {
            return ApiResponse.builder().
                    message("Category Not Found").
                    status(204).
                    success(false).
                    build();
        }

        MultipartFile photo = productDTO.getPhoto();
        Attachment attachment = new Attachment();
        attachment.setBytes(photo.getBytes());
        attachment.setOriginalName(photo.getOriginalFilename());

        Product product = optionalProduct.get();
        product.setNameUz(productDTO.getNameUz());
        product.setNameRu(productDTO.getNameRu());
        product.setNameEn(productDTO.getNameEn());
        product.setDescriptionUz(productDTO.getNameUz());
        product.setDescriptionRu(productDTO.getDescriptionRu());
        product.setDescriptionEn(productDTO.getDescriptionEn());
        product.setPrice(productDTO.getPrice());
        product.setCategory(optionalCategory.get());
        product.setAttachment(attachment);

        productRepository.save(product);

        return ApiResponse.builder().
                message("Edited !").
                status(200).
                success(true).
                build();
    }

    public ApiResponse<?> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().
                    message("Product Not Found").
                    status(204).
                    success(false).
                    build();
        }
        productRepository.deleteById(id);
        return ApiResponse.builder().
                message("Deleted").
                status(201).
                success(true).
                build();
    }
}
