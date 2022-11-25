package com.example.tedabot.api.controller;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.bot.model.Request;
import com.example.tedabot.api.service.SiteService;
import com.example.tedabot.bot.model.SiteHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 18:21 *
 */

@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Request request){
        ApiResponse<?> response = siteService.add(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/history")
    public ResponseEntity<?>main(@RequestBody SiteHistory history){
        ApiResponse<?> response = siteService.historyWriter(history);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
