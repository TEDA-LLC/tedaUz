package com.example.tedabot.bot;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
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
            Message message = update.getMessage();
            Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
            if (message.hasText()) {
                if (message.getText().equals("/start")) {
                    if (optionalUser.isPresent()) {
                        currentUser = optionalUser.get();
                        currentUser.setState(State.START);
                        currentUser.setFullName(update.getMessage().getFrom().getFirstName());
                        userRepository.save(currentUser);
                    } else {
                        currentUser = new User();
                        currentUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                        currentUser.setFullName(message.getFrom().getFirstName());
                        currentUser.setState(State.START);
                        userRepository.save(currentUser);
                    }
                    execute(botService.start(update));
                } else if (message.getText().equals(ConstantUz.BUTTON)) {
                    currentUser = optionalUser.get();
                    currentUser.setLanguage(Language.UZB);
                    userRepository.save(currentUser);
                    execute(botService.language(update, Language.UZB));
                } else if (message.getText().equals(ConstantRu.BUTTON)) {
                    currentUser = optionalUser.get();
                    currentUser.setLanguage(Language.RUS);
                    userRepository.save(currentUser);
                    execute(botService.language(update, Language.RUS));
                } else {
                    currentUser = optionalUser.get();
                    switch (currentUser.getState()) {
                        case CONTACT:
                            currentUser.setState(State.MENU);
                            if (message.getText().equals(ConstantUz.ABOUT_US_BUTTON) || message.getText().equals(ConstantRu.ABOUT_US_BUTTON)) {
                                execute(botService.aboutUs(update, currentUser.getLanguage()));
                            } else if (message.getText().equals(ConstantUz.TO_ADMIN_BUTTON) || message.getText().equals(ConstantRu.TO_ADMIN_BUTTON)) {
                                execute(botService.toAdmin(update, currentUser.getLanguage()));
                            } else if (message.getText().equals(ConstantUz.SETTINGS_BUTTON) || message.getText().equals(ConstantRu.SETTINGS_BUTTON)) {
                                currentUser.setState(State.SETTINGS);
                                userRepository.save(currentUser);
                                execute(botService.settings(update, currentUser.getLanguage()));
                            }
                            break;
                        case SETTINGS:
                            if (message.getText().equals(ConstantUz.LANGUAGE) || message.getText().equals(ConstantRu.LANGUAGE)) {
                                currentUser.setState(State.LANGUAGE);
                                userRepository.save(currentUser);
                                execute(botService.editLanguage(update, currentUser.getLanguage()));
                            }

                            break;
                        case LANGUAGE:
                            if (message.getText().equals(ConstantUz.LANGUAGE_ICON)) {
                                currentUser.setState(State.SETTINGS);
                                currentUser.setLanguage(Language.UZB);
                                userRepository.save(currentUser);
                            } else if (message.getText().equals(ConstantRu.LANGUAGE_ICON)) {
                                currentUser.setState(State.SETTINGS);
                                currentUser.setLanguage(Language.RUS);
                                userRepository.save(currentUser);
                            }
                            execute(botService.languageEdited(update,currentUser.getLanguage()));
                    }
                }
            } else if (message.hasContact()) {
                switch (optionalUser.get().getState()) {
                    case START:
                        User user = optionalUser.get();
                        user.setPhone(message.getContact().getPhoneNumber());
                        user.setState(State.CONTACT);
                        userRepository.save(user);
                        execute(botService.contact(update, user.getLanguage()));
                        break;
                    case SETTINGS:
                        User user1 = optionalUser.get();
                        user1.setPhone(message.getContact().getPhoneNumber());
                        user1.setState(State.SETTINGS);
                        userRepository.save(user1);
                        execute(botService.editContact(update, user1.getLanguage()));
                        break;
                }
            }
        }


    }
}
