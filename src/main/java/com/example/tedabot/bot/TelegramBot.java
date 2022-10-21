package com.example.tedabot.bot;

import com.example.tedabot.repository.UserRepository;
import com.example.tedabot.service.AdminService;
import com.example.tedabot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        if (update.hasMessage()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            String message = update.getMessage().getText();
            if (message.equals("/start")) {
                execute(botService.start(chatId, update));
            }
        } else if (update.hasCallbackQuery()) {

        }
    }
}
