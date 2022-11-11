package com.example.tedabot.api.controller;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.service.UserService;
import com.example.tedabot.model.User;
import com.example.tedabot.model.UserHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:00 *
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<List<User>> response = userService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getAllHistory(HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<List<UserHistory>> response = userService.getUserHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/amount/{productId}")
    public ResponseEntity<?> getAmountByProduct(@PathVariable Long productId, HttpServletRequest request) {
        userService.validateToken(request);
        ApiResponse<?> response = userService.getAmountByProduct(productId);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
