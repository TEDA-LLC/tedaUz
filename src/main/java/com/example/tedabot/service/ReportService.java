package com.example.tedabot.service;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.model.*;
import com.example.tedabot.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:02 *
 */
@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final ProductRepository productRepository;
    private final WordHistoryRepository wordHistoryRepository;
    private final RequestRepository requestRepository;

    public ApiResponse<List<User>> getAll() {
        List<User> users = userRepository.findAll();
        return ApiResponse.<List<User>>builder().
                message("Here").
                status(200).
                success(true).
                data(users).
                build();
    }

    public ApiResponse<List<UserHistory>> getUserHistory() {
        List<UserHistory> histories = userHistoryRepository.findAll();
        return ApiResponse.<List<UserHistory>>builder().
                message("Here").
                status(200).
                success(true).
                data(histories).
                build();
    }

    public ApiResponse<?> getAmountByProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Product Not Found").
                    status(204).
                    success(false).
                    build();
        } else {
            long amount = userHistoryRepository.getAmountByProduct(productId);
            return ApiResponse.builder().
                    message("Here").
                    status(200).
                    success(true).
                    data(amount).
                    build();
        }
    }
    public ApiResponse<List<WordHistory>> getWordsHistory() {
        List<WordHistory> history = wordHistoryRepository.findAll();
        return ApiResponse.<List<WordHistory>>builder().
                message("Here").
                status(200).
                success(true).
                data(history).
                build();
    }

    public ApiResponse<?> editView(Long requestId) {
            Optional<Request> requestOptional = requestRepository.findById(requestId);
            if (requestOptional.isEmpty()) {
                return ApiResponse.builder().
                        message("Request id not found !").
                        status(400).
                        success(false).
                        build();
            }
            Request request = requestOptional.get();
            request.setView(true);
            requestRepository.save(request);
            return ApiResponse.builder().
                    message("Edited !").
                    status(201).
                    success(true).
                    build();
    }
}
