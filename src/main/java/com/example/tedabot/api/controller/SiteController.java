package com.example.tedabot.api.controller;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.dto.RequestDTO;
import com.example.tedabot.bot.model.Request;
import com.example.tedabot.api.service.SiteService;
import com.example.tedabot.bot.model.SiteHistory;
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
    public ResponseEntity<?> getRequest(@RequestParam(defaultValue = "0") int page) {
        ApiResponse<List<Request>> response = siteService.getRequest();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestParam(defaultValue = "0") int page) {
        ApiResponse<List<SiteHistory>> response = siteService.getSiteHistory();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RequestDTO dto) {
        ApiResponse<?> response = siteService.add(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/history")
    public ResponseEntity<?> main(@RequestBody SiteHistory history, @RequestParam String phone, @RequestParam String email) {
        ApiResponse<?> response = siteService.historyWriter(history, phone, email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
