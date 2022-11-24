package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.model.Request;
import com.example.tedabot.bot.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:28   *  tedaUz
 */
@Service
@RequiredArgsConstructor
public class SiteService {

    private final RequestRepository requestRepository;

    public ApiResponse<?> add(Request request) {
        Request save = requestRepository.save(request);
        return ApiResponse.builder().
                message("Request is added").
                status(201).
                success(true).
                data(save).
                build();
    }
}
