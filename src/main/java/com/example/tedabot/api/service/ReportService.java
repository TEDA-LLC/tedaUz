package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.bot.model.Product;
import com.example.tedabot.bot.model.User;
import com.example.tedabot.bot.model.UserHistory;
import com.example.tedabot.bot.model.WordHistory;
import com.example.tedabot.bot.repository.ProductRepository;
import com.example.tedabot.bot.repository.UserHistoryRepository;
import com.example.tedabot.bot.repository.UserRepository;
import com.example.tedabot.bot.repository.WordHistoryRepository;
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
}
