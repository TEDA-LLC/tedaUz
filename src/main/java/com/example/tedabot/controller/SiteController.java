package com.example.tedabot.controller;

import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.RequestDTO;
import com.example.tedabot.dto.ReviewDTO;
import com.example.tedabot.model.Request;
import com.example.tedabot.model.Review;
import com.example.tedabot.model.SiteHistory;
import com.example.tedabot.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 18:21 *
 */

@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @GetMapping("/request")
    public ResponseEntity<?> getRequest() {
        ApiResponse<List<Request>> response = siteService.getRequest();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() {
        ApiResponse<List<SiteHistory>> response = siteService.getSiteHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RequestDTO dto) {
        ApiResponse<?> response = siteService.add(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/history")
    public ResponseEntity<?> main(@RequestBody SiteHistory history, @RequestParam(required = false) String phone, @RequestParam(required = false) String email) {
        ApiResponse<?> response = siteService.historyWriter(history, phone, email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO dto) {
        ApiResponse<?> response = siteService.addReview(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/review")
    public ResponseEntity<?> editStatusReview(@RequestParam Long id) {
        ApiResponse<?> response = siteService.editStatusReview(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) {
        ApiResponse<Review> response = siteService.getReview(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/review")
    public ResponseEntity<?> getReviews(@RequestParam Boolean active) {
        ApiResponse<List<Review>> response = siteService.getReviews(active);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/reviewusers")
    public ResponseEntity<?> getReviewforUsers(){
        ApiResponse<List<Review>> response = siteService.getReviewforUsers();
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
