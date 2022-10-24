package com.example.tedabot.bot;

import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.constant.enums.State;
import com.example.tedabot.model.User;
import com.example.tedabot.repository.UserRepository;
import com.example.tedabot.service.AdminService;
import com.example.tedabot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 15:21 *
 */

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram_bot_username}")
    String username;
    @Value("${telegram_bot_botToken}")
    String botToken;

    private final BotService botService;
    private final AdminService adminService;
    private final UserRepository userRepository;


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        User currentUser;

        if (update.hasMessage()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            Message message = update.getMessage();
            Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
            if (message.hasText()) {
                if (message.getText().equals("/start")) {
                    if (optionalUser.isPresent()) {
                        currentUser = optionalUser.get();
                        currentUser.setState(State.START);
                        currentUser.setLanguage(Language.NONE);
                        currentUser.setFullName(update.getMessage().getFrom().getFirstName());
                        userRepository.save(currentUser);
                    } else {
                        currentUser = new User();
                        currentUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                        currentUser.setFullName(message.getFrom().getFirstName());
                        currentUser.setLanguage(Language.NONE);
                        currentUser.setState(State.START);
                        userRepository.save(currentUser);
                    }
                    execute(botService.welcome(update));
                } else {
                    currentUser = optionalUser.get();
                    switch (currentUser.getLanguage()) {
                        case NONE:
                            execute(botService.language(update));
                            break;
                        case RUS:
                            break;
                        case UZB:
                            switch (currentUser.getState()) {
                                case START :
                            }
                            break;
                    }
                }
            }
        }


    }
