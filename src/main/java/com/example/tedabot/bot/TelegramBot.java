package com.example.tedabot.bot;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.constant.enums.State;
import com.example.tedabot.model.User;
import com.example.tedabot.repository.UserRepository;
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
    @Value("${telegram.bot.username}")
    String username;
    @Value("${telegram.bot.token}")
    String botToken;

    private final BotService botService;
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

        System.out.println(update.toString());
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                User currentUser;
                if (update.hasMessage()) {
                    Message message = update.getMessage();
                    String chatId = String.valueOf(message.getChatId());
                    Optional<User> optionalUser = userRepository.findByChatId(chatId);
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
                                currentUser.setUsername(message.getFrom().getUserName());
                                currentUser.setState(State.START);
                                userRepository.save(currentUser);
                            }
                            execute(botService.start(chatId));
                        } else if (message.getText().equals(ConstantUz.BUTTON)) {
                            currentUser = optionalUser.get();
                            currentUser.setLanguage(Language.UZB);
                            userRepository.save(currentUser);
                            execute(botService.language(chatId, Language.UZB));
                        } else if (message.getText().equals(ConstantRu.BUTTON)) {
                            currentUser = optionalUser.get();
                            currentUser.setLanguage(Language.RUS);
                            userRepository.save(currentUser);
                            execute(botService.language(chatId, Language.RUS));
                        } else {
                            currentUser = optionalUser.get();
                            switch (currentUser.getState()) {
                                case CONTACT -> {
                                    if (message.getText().equals(ConstantUz.ABOUT_US_BUTTON) || message.getText().equals(ConstantRu.ABOUT_US_BUTTON)) {
                                        execute(botService.aboutUs(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.TO_ADMIN_BUTTON) || message.getText().equals(ConstantRu.TO_ADMIN_BUTTON)) {
                                        execute(botService.toAdmin(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.SETTINGS_BUTTON) || message.getText().equals(ConstantRu.SETTINGS_BUTTON)) {
                                        currentUser.setState(State.SETTINGS);
                                        userRepository.save(currentUser);
                                        execute(botService.settings(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.SERVICES_BUTTON) || message.getText().equals(ConstantRu.SERVICES_BUTTON)) {
                                        currentUser.setState(State.SERVICE);
                                        userRepository.save(currentUser);
                                        execute(botService.services(chatId, currentUser.getLanguage()));
                                    }
                                }
                                case SETTINGS -> {
                                    if (message.getText().equals(ConstantUz.LANGUAGE) || message.getText().equals(ConstantRu.LANGUAGE)) {
                                        currentUser.setState(State.LANGUAGE);
                                        userRepository.save(currentUser);
                                        execute(botService.editLanguage(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.BACK) || message.getText().equals(ConstantRu.BACK)) {
                                        currentUser.setState(State.CONTACT);
                                        userRepository.save(currentUser);
                                        execute(botService.ok(chatId, currentUser.getLanguage()));
                                    }

                                }
                                case LANGUAGE -> {
                                    if (message.getText().equals(ConstantUz.LANGUAGE_ICON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLanguage(Language.UZB);
                                        userRepository.save(currentUser);
                                    } else if (message.getText().equals(ConstantRu.LANGUAGE_ICON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLanguage(Language.RUS);
                                        userRepository.save(currentUser);
                                    }
                                    execute(botService.edited(chatId, currentUser.getLanguage()));
                                }
                                case SERVICE -> {
                                    Long categoryId = botService.getCategoryId(message.getText());
                                    if (categoryId != null) {
                                        execute(botService.products(categoryId, currentUser.getLanguage(), chatId));
                                    } else if (message.getText().equals(ConstantUz.BACK) || message.getText().equals(ConstantRu.BACK)) {
                                        currentUser.setState(State.CONTACT);
                                        userRepository.save(currentUser);
                                        execute(botService.ok(chatId, currentUser.getLanguage()));
                                    }
                                }
                            }
                        }
                    } else if (message.hasContact()) {
                        switch (optionalUser.get().getState()) {
                            case START -> {
                                User user = optionalUser.get();
                                user.setPhone(message.getContact().getPhoneNumber());
                                user.setState(State.CONTACT);
                                userRepository.save(user);
                                execute(botService.contact(chatId, user.getLanguage()));
                            }
                            case SETTINGS -> {
                                User user1 = optionalUser.get();
                                user1.setPhone(message.getContact().getPhoneNumber());
                                userRepository.save(user1);
                                execute(botService.edited(chatId, user1.getLanguage()));
                            }
                        }
                    }
                } else if (update.hasCallbackQuery()) {
                    String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                    Optional<User> optionalUser = userRepository.findByChatId(chatId);
                    currentUser = optionalUser.get();
                    switch (currentUser.getState()) {
                        case SERVICE -> {
                            if (update.getCallbackQuery().getData().startsWith("$back")) {
                                execute(botService.deleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId()));
                                execute(botService.services(chatId, currentUser.getLanguage()));
                            } else {
                                currentUser.setState(State.PRODUCT);
                                userRepository.save(currentUser);
                                execute(botService.deleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId()));
                                execute(botService.getProduct(update, currentUser));
                            }
                        }
                        case PRODUCT -> {
                            if (update.getCallbackQuery().getData().startsWith("$back")) {
                                currentUser.setState(State.SERVICE);
                                userRepository.save(currentUser);
                                execute(botService.deleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId()));
                                execute(botService.backToProducts(update, chatId, currentUser.getLanguage()));
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
