package com.example.tedabot.api;

import com.example.tedabot.model.Attachment;
import com.example.tedabot.model.Category;
import com.example.tedabot.model.Product;
import com.example.tedabot.repository.AttachmentRepository;
import com.example.tedabot.repository.CategoryRepository;
import com.example.tedabot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:24 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

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
        product.setDescriptionRu(productDTO.getDescriptionRu());
        product.setDescriptionUz(productDTO.getDescriptionUz());

        productRepository.save(product);

        return ApiResponse.builder().
                message("Saved").
                status(200).
                success(true).
                build();
    }
}
