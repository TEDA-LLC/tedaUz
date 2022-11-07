package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.model.Product;
import com.example.tedabot.model.User;
import com.example.tedabot.model.UserHistory;
import com.example.tedabot.repository.ProductRepository;
import com.example.tedabot.repository.UserHistoryRepository;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:02 *
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final ProductRepository productRepository;
    @Value("${telegram.bot.token}")
    String botToken;

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

    @SneakyThrows
    public void validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.length() > 8) {
            token = token.substring(7);
            if (token.equals(botToken)) {
                return;
            }
        }
           throw new AccessDeniedException("Forbidden !");
    }

    public ApiResponse<?> error() {
        return ApiResponse.builder()
                .success(false)
                .status(403)
                .message("Forbidden !")
                .build();
    }
}
