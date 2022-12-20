package com.example.tedabot.service;

import com.example.tedabot.bot.TelegramBot;
import com.example.tedabot.dto.ApiResponse;
import com.example.tedabot.dto.RequestDTO;
import com.example.tedabot.dto.ReviewDTO;
import com.example.tedabot.model.Request;
import com.example.tedabot.model.Review;
import com.example.tedabot.model.SiteHistory;
import com.example.tedabot.model.User;
import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.repository.RequestRepository;
import com.example.tedabot.repository.ReviewRepository;
import com.example.tedabot.repository.SiteHistoryRepository;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Mansurov Abdusamad  *  24.11.2022  *  10:28   *  tedaUz
 */
@Service
@RequiredArgsConstructor
public class SiteService {
    private final RequestRepository requestRepository;
    private final SiteHistoryRepository siteHistoryRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TelegramBot telegramBot;

    public ApiResponse<?> add(RequestDTO dto) {
        Request request = new Request();
        request.setRegisteredType(RegisteredType.WEBSITE);
        request.setAboutProduct(dto.getAboutProduct());
        request.setDateTime(LocalDateTime.now());

        if (dto.getCategory() != null)
            request.setCategory(dto.getCategory());

        if (dto.getPhone() != null) {
            Optional<User> userOptional = userRepository.findByPhone(dto.getPhone());
            if (userOptional.isEmpty()) {
                User user = new User();
                user.setPhone(dto.getPhone());
                user.setFullName(dto.getName());
                user.setRegisteredTime(LocalDateTime.now());
                user.setLastOperationTime(LocalDateTime.now());
                user.setCount(1);
                if (dto.getEmail() != null)
                    user.setEmail(dto.getEmail());
                User save = userRepository.save(user);
                request.setUser(save);
            } else {
                User user = userOptional.get();
                user.setLastOperationTime(LocalDateTime.now());
                User save = userRepository.save(user);
                request.setUser(save);
            }
        }

        Request save = requestRepository.save(request);
        return ApiResponse.builder().
                message("Request was added !").
                status(201).
                success(true).
                data(save).
                build();
    }

    public ApiResponse<?> historyWriter(SiteHistory history, String phone, String email) {
        if (phone != null) {
            Optional<User> userOptional = userRepository.findByPhone(phone);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setLastOperationTime(LocalDateTime.now());
                user.setCount(user.getCount() + 1);
                if (user.getEmail() == null) {
                    user.setEmail(email);
                }
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

    public ApiResponse<?> addReview(ReviewDTO dto) {
        Optional<User> userOptionalByPhone = userRepository.findByPhone(dto.getPhone());
        if (userOptionalByPhone.isEmpty()) {
            return ApiResponse.builder().
                    message("User not found !").
                    status(400).
                    success(false).
                    build();
        }

        User user = userOptionalByPhone.get();

        if (user.getEmail() == null)
            user.setEmail(dto.getEmail());

        Review review = new Review();
        review.setText(dto.getText());
        review.setUser(user);
        reviewRepository.save(review);

        return ApiResponse.builder().
                message("Review saved !").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<?> editStatusReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Review not found !").
                    status(400).
                    success(false).
                    build();
        }

        Review review = reviewOptional.get();

        review.setConfirmation(!review.isConfirmation());
        Review save = reviewRepository.save(review);
        return ApiResponse.builder().
                message("Review edited!").
                status(200).
                success(true).
                data(save).
                build();
    }

    public ApiResponse<Review> getReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            return ApiResponse.<Review>builder().
                    message("Review saved !").
                    status(200).
                    success(true).
                    data(reviewOptional.get()).
                    build();
        }
        return ApiResponse.<Review>builder().
                message("Review not found !").
                status(400).
                success(false).
                build();
    }

    public ApiResponse<List<Review>> getReviews(Boolean active) {
        List<Review> reviewList = null;

        if (active == null)
            reviewList = reviewRepository.findAll();

        if (Boolean.FALSE.equals(active))
            reviewList = reviewRepository.findAllByConfirmationFalse();

        if (Boolean.TRUE.equals(active))
            reviewList = reviewRepository.findAllByConfirmationTrue();

        assert reviewList != null;
        reviewList.sort(Comparator.comparing(Review::getDateTime));

        if (reviewList.isEmpty())
            return ApiResponse.<List<Review>>builder().
                    message("Reviews aren't found !").
                    status(400).
                    success(false).
                    build();

        return ApiResponse.<List<Review>>builder().
                message("Reviews here!").
                status(200).
                success(true).
                data(reviewList).
                build();
    }

    public ApiResponse<List<Review>> getReviewforUsers() {

        List<Review> reviewList = reviewRepository.findAllByConfirmationTrueForUsers();

        if (reviewList.isEmpty()) {
            return ApiResponse.<List<Review>>builder().
                    message("Reviews aren't found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<Review>>builder().
                message("Reviews here!").
                status(200).
                success(true).
                data(reviewList).
                build();
    }

    public ApiResponse<Map<String, List<Request>>> getRequestsByUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return ApiResponse.<Map<String, List<Request>>>builder().
                    message("User not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        List<Request> requestList = requestRepository.findAllByUser(userOptional.get(), Sort.by(Sort.Direction.ASC, "date_time"));
        Map<String, List<Request>> collect = requestList.stream().collect(Collectors.groupingBy(Request::getCategory));
        return ApiResponse.<Map<String, List<Request>>>builder().
                message("Here!!!").
                success(true).
                status(200).
                data(collect).
                build();
    }

    @SneakyThrows
    public ApiResponse<?> sendMessage(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Request not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        Request request = requestOptional.get();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Sizning " + request.getId() + " raqamli murojatingizni " + request.getEmployee().getFullName() + " qabul qildi.");
        sendMessage.setChatId(request.getUser().getChatId());
        telegramBot.execute(sendMessage);

        return ApiResponse.builder().
                message("Sent!!!").
                success(true).
                status(200).
                build();
    }
}


