package com.example.tedabot.api;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:23 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute ProductDTO productDTO){
        ApiResponse<?> response = productService.save(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
