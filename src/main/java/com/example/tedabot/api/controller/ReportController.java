package com.example.tedabot.api.controller;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.service.ReportService;
import com.example.tedabot.api.service.SiteService;
import com.example.tedabot.bot.model.User;
import com.example.tedabot.bot.model.UserHistory;
import com.example.tedabot.bot.model.WordHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:00 *
 */
@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @GetMapping("/auth")
    public ResponseEntity<?> checkAuth(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        ApiResponse<List<User>> response = reportService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/userHistory")
    public ResponseEntity<?> getUserHistory() {
        ApiResponse<List<UserHistory>> response = reportService.getUserHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/amount/{productId}")
    public ResponseEntity<?> getAmountByProduct(@PathVariable Long productId) {
        ApiResponse<?> response = reportService.getAmountByProduct(productId);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/wordHistory")
    public ResponseEntity<?> getAllHistory() {
        ApiResponse<List<WordHistory>> response = reportService.getWordsHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/view/{requestId}")
    public ResponseEntity<?> editView(@PathVariable Long requestId){
        ApiResponse<?> response = reportService.editView(requestId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
