package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.bot.model.Request;
import com.example.tedabot.bot.model.SiteHistory;
import com.example.tedabot.bot.model.enums.RequestType;
import com.example.tedabot.bot.repository.RequestRepository;
import com.example.tedabot.bot.repository.SiteHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:28   *  tedaUz
 */
@Service
@RequiredArgsConstructor
public class SiteService {
    private final RequestRepository requestRepository;
    private final SiteHistoryRepository siteHistoryRepository;

    public ApiResponse<?> add(Request request) {
        request.setRequestType(RequestType.WEBSITE);
        Request save = requestRepository.save(request);
        return ApiResponse.builder().
                message("Request was added !").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<?> historyWriter(SiteHistory history) {
        siteHistoryRepository.save(history);
        return ApiResponse.builder().
                message("History created !").
                status(201).
                success(true).
                build();
    }
}
