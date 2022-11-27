package com.example.tedabot.api.service;

import com.example.tedabot.api.dto.ApiResponse;
import com.example.tedabot.api.dto.RequestDTO;
import com.example.tedabot.bot.model.Request;
import com.example.tedabot.bot.model.SiteHistory;
import com.example.tedabot.bot.model.User;
import com.example.tedabot.bot.model.enums.RequestType;
import com.example.tedabot.bot.repository.RequestRepository;
import com.example.tedabot.bot.repository.SiteHistoryRepository;
import com.example.tedabot.bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:28   *  tedaUz
 */
@Service
@RequiredArgsConstructor
public class SiteService {
    private final RequestRepository requestRepository;
    private final SiteHistoryRepository siteHistoryRepository;

    private final UserRepository userRepository;

    public ApiResponse<?> add(RequestDTO dto) {
        Request request = new Request();
        request.setRequestType(RequestType.WEBSITE);
        request.setAboutProduct(dto.getAboutProduct());

        if (dto.getCategory() != null)
            request.setCategory(dto.getCategory());

        if (dto.getPhone() != null) {
            Optional<User> userOptional = userRepository.findByPhone(dto.getPhone());
            if (userOptional.isEmpty()) {
                User user = new User();
                user.setPhone(dto.getPhone());
                user.setFullName(dto.getName());
                user.setCount(1);
                User save = userRepository.save(user);
                request.setUser(save);
            }
            request.setUser(userOptional.get());
        }

        Request save = requestRepository.save(request);
        return ApiResponse.builder().
                message("Request was added !").
                status(201).
                success(true).
                data(save).
                build();
    }

    public ApiResponse<?> historyWriter(SiteHistory history, String phone) {

        if (phone != null){
            Optional<User> userOptional = userRepository.findByPhone(phone);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setCount(user.getCount() + 1);
                history.setUser(user);
                userRepository.save(user);
            }
        }

        siteHistoryRepository.save(history);
        return ApiResponse.builder().
                message("History created !").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<List<Request>> getRequest() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) {
            return ApiResponse.<List<Request>>builder().
                    message("Requests are not found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<Request>>builder().
                message("Requests here !").
                status(200).
                success(true).
                data(requests).
                build();
    }

    public ApiResponse<List<SiteHistory>> getSiteHistory() {
        List<SiteHistory> siteHistories = siteHistoryRepository.findAll();
        if (siteHistories.isEmpty()) {
            return ApiResponse.<List<SiteHistory>>builder().
                    message("History are not found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<SiteHistory>>builder().
                message("History here !").
                status(200).
                success(true).
                data(siteHistories).
                build();
    }
}