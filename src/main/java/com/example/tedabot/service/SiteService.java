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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        boolean isEmail = dto.getEmail() == null || !dto.getEmail().equals("");
        boolean isPhone = dto.getPhone() == null || !dto.getPhone().equals("");
        Request request = new Request();
        request.setRegisteredType(RegisteredType.WEBSITE);
        request.setAboutProduct(dto.getAboutProduct());
        request.setDateTime(LocalDateTime.now());

        if (dto.getCategory() != null)
            request.setCategory(dto.getCategory());

        if (isPhone) {
            Optional<User> userOptionalByPhone = userRepository.findByPhone(dto.getPhone());
            if (userOptionalByPhone.isPresent()) {
                User user = userOptionalByPhone.get();
                if (dto.getName() != null && !dto.getName().equals(""))
                    user.setFullName(dto.getName());
                user.setLastOperationTime(LocalDateTime.now());
                user.setCount(user.getCount() + 1);
                user.setEmail(dto.getEmail());
                User userSave = userRepository.save(user);
                request.setUser(userSave);
                Request save = requestRepository.save(request);
                return ApiResponse.builder().
                        message("Request was added !").
                        status(201).
                        success(true).
                        data(save).
                        build();
            }
        }

        if (isEmail) {
            Optional<User> userOptionalByEmail = userRepository.findByEmail(dto.getEmail());
            if (userOptionalByEmail.isPresent()) {
                User user = userOptionalByEmail.get();
                user.setLastOperationTime(LocalDateTime.now());
                user.setCount(user.getCount() + 1);
                user.setPhone(dto.getPhone());
                User userSave = userRepository.save(user);
                request.setUser(userSave);
                Request save = requestRepository.save(request);
                return ApiResponse.builder().
                        message("Request was added !").
                        status(201).
                        success(true).
                        data(save).
                        build();
            }
        }


        User user = new User();
        user.setFullName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setCount(1);
        user.setEmail(dto.getEmail());
        user.setRegisteredTime(LocalDateTime.now());
        user.setRegisteredType(RegisteredType.WEBSITE);
        User userSave = userRepository.save(user);
        request.setUser(userSave);
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
        List<Request> requestList = requestRepository.findAllByUser(userOptional.get(), Sort.by(Sort.Direction.ASC, "dateTime"));
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

    public ApiResponse<User> login(String email, String phone) {
        boolean isEmail = email != null;
        boolean isPhone = phone != null;
        if (isEmail) {
            isEmail = !email.equals("");
        }
        if (isPhone) {
            isPhone = !phone.equals("");
        }
        if (isEmail && isPhone) {
            Optional<User> userOptionalByEmail1 = userRepository.findByEmail(email);
            Optional<User> userOptionalByPhone1 = userRepository.findByPhone(phone);
            if (userOptionalByPhone1.isPresent() && userOptionalByEmail1.isPresent()) {
                User userByEmail = userOptionalByEmail1.get();
                User userByPhone = userOptionalByPhone1.get();
                if (!userByPhone.getId().equals(userByEmail.getId())) {
                    userByPhone.setEmail(userByEmail.getEmail());
                    User save = userRepository.save(userByPhone);
                    userRepository.delete(userByEmail);
                    return ApiResponse.<User>builder().
                            message("User updated!!!").
                            success(true).
                            status(200).
                            data(save).
                            build();
                }
            }

        }
        if (isEmail) {
            Optional<User> userOptionalByEmail = userRepository.findByEmail(email);
            if (userOptionalByEmail.isPresent()) {
                User userByEmail = userOptionalByEmail.get();
                if (userByEmail.getPhone() == null) {
                    userByEmail.setPhone(phone);
                }
                userByEmail.setCount(userByEmail.getCount() + 1);
                User save = userRepository.save(userByEmail);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        } else if (isPhone) {
            Optional<User> userOptionalByPhone = userRepository.findByPhone(phone);
            if (userOptionalByPhone.isPresent()) {
                User userByPhone = userOptionalByPhone.get();
                if (userByPhone.getEmail() == null) {
                    userByPhone.setEmail(email);
                }
                userByPhone.setCount(userByPhone.getCount() + 1);
                User save = userRepository.save(userByPhone);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        }

        if (!isEmail && !isPhone)
            return ApiResponse.<User>builder().
                    message("Parameters are required!!!").
                    success(false).
                    status(400).
                    build();
        if (isEmail && !email.contains("@"))
            return ApiResponse.<User>builder().
                    message("Email type is not supported!!!").
                    success(false).
                    status(400).
                    build();
        if (isPhone) {
            try {
                Long.parseLong(phone.substring(1));
            } catch (NumberFormatException e) {
                return ApiResponse.<User>builder().
                        message("Phone is not numeric!!!").
                        success(false).
                        status(400).
                        build();
            }
        }

        if (isPhone && !phone.startsWith("+"))
            return ApiResponse.<User>builder().
                    message("Phone is not numeric!!!").
                    success(false).
                    status(400).
                    build();


        User user = new User();
        if (isEmail) {
            user.setEmail(email);
        }
        if (isPhone) {
            user.setPhone(phone);
        }
        user.setCount(1);
        user.setRegisteredType(RegisteredType.WEBSITE);
        User save = userRepository.save(user);
        return ApiResponse.<User>builder().
                message("User saved!!!").
                success(true).
                status(201).
                data(save).
                build();

    }
}


