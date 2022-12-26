package com.example.tedabot.bot;

import com.example.tedabot.bot.constant.ConstantEn;
import com.example.tedabot.bot.constant.ConstantRu;
import com.example.tedabot.bot.constant.ConstantUz;
import com.example.tedabot.model.Bot;
import com.example.tedabot.model.enums.Language;
import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.model.enums.State;
import com.example.tedabot.model.User;
import com.example.tedabot.repository.BotRepository;
import com.example.tedabot.repository.UserRepository;
import com.example.tedabot.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 15:21 *
 */

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String userName;
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.id}")
    private Long botId;
    private final BotService botService;
    private final UserRepository userRepository;
    private final BotRepository botRepository;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                User currentUser;
                if (update.hasMessage()) {
                    Optional<Bot> botOptional = botRepository.findById(botId);
                    Bot bot = botOptional.get();
                    Message message = update.getMessage();
                    String chatId = String.valueOf(message.getChatId());
                    Optional<User> optionalUser = userRepository.findByBot_IdAndChatId(botId, chatId);
                    if (message.hasText()) {
                        if (message.getText().equals("/start")) {
                            if (optionalUser.isPresent()) {
                                currentUser = optionalUser.get();
                                currentUser.setState(State.START);
                                currentUser.setFullName(update.getMessage().getFrom().getFirstName());
                            } else {
                                currentUser = new User();
                                currentUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                                currentUser.setRegisteredType(RegisteredType.BOT);
                                currentUser.setFullName(message.getFrom().getFirstName());
                                currentUser.setUsername(message.getFrom().getUserName());
                                currentUser.setState(State.START);
                                currentUser.setBot(bot);
                            }
                            currentUser.setLastOperationTime(LocalDateTime.now());
                            userRepository.save(currentUser);
                            execute(botService.start(chatId));
                        } else if (message.getText().equals(ConstantUz.BUTTON)) {
                            currentUser = optionalUser.get();
                            currentUser.setLanguage(Language.UZB);
                            currentUser.setLastOperationTime(LocalDateTime.now());
                            userRepository.save(currentUser);
                            execute(botService.language(chatId, Language.UZB));
                        } else if (message.getText().equals(ConstantRu.BUTTON)) {
                            currentUser = optionalUser.get();
                            currentUser.setLanguage(Language.RUS);
                            currentUser.setLastOperationTime(LocalDateTime.now());
                            userRepository.save(currentUser);
                            execute(botService.language(chatId, Language.RUS));
                        } else if (message.getText().equals(ConstantEn.BUTTON)) {
                            currentUser = optionalUser.get();
                            currentUser.setLanguage(Language.ENG);
                            currentUser.setLastOperationTime(LocalDateTime.now());
                            userRepository.save(currentUser);
                            execute(botService.language(chatId, Language.ENG));
                        } else {
                            currentUser = optionalUser.get();
                            switch (currentUser.getState()) {
                                case CONTACT -> {
                                    if (message.getText().equals(ConstantUz.ABOUT_US_BUTTON) || message.getText().equals(ConstantRu.ABOUT_US_BUTTON) || message.getText().equals(ConstantEn.ABOUT_US_BUTTON)) {
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.aboutUs(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.VACANCY_BUTTON) || message.getText().equals(ConstantRu.VACANCY_BUTTON) || message.getText().equals(ConstantEn.VACANCY_BUTTON)) {
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.vacancies(chatId, currentUser));
                                        execute(botService.menu(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.MY_REQUESTS) || message.getText().equals(ConstantRu.MY_REQUESTS) || message.getText().equals(ConstantEn.MY_REQUESTS)) {
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.myRequests(chatId, currentUser));
                                        execute(botService.menu(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.SETTINGS_BUTTON) || message.getText().equals(ConstantRu.SETTINGS_BUTTON) || message.getText().equals(ConstantEn.SETTINGS_BUTTON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.settings(chatId, currentUser.getLanguage()));
                                    } else if ((message.getText().matches("(" + ConstantUz.SYSTEMS_BUTTON + "|" + ConstantRu.SYSTEMS_BUTTON + "|" + ConstantEn.SYSTEMS_BUTTON + "|" + ConstantUz.SERVICES_BUTTON + "|" + ConstantRu.SERVICES_BUTTON + "|" + ConstantEn.SERVICES_BUTTON + ")"))) {
                                        Long categoryId = botService.getCategoryIdByName(message.getText());
                                        if (categoryId != null) {
                                            execute(botService.products(categoryId, currentUser.getLanguage(), chatId));
                                        } else if (message.getText().equals(ConstantUz.BACK) || message.getText().equals(ConstantRu.BACK) || message.getText().equals(ConstantEn.BACK)) {
                                            currentUser.setState(State.CONTACT);
                                            currentUser.setLastOperationTime(LocalDateTime.now());
                                            userRepository.save(currentUser);
                                            execute(botService.ok(chatId, currentUser.getLanguage()));
                                        }
                                    } else {
                                        botService.storyWriter(currentUser, message);
                                    }
                                }
                                case SETTINGS -> {
                                    if (message.getText().equals(ConstantUz.LANGUAGE) || message.getText().equals(ConstantRu.LANGUAGE) || message.getText().equals(ConstantEn.LANGUAGE)) {
                                        currentUser.setState(State.LANGUAGE);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.editLanguage(chatId, currentUser.getLanguage()));
                                    } else if (message.getText().equals(ConstantUz.BACK) || message.getText().equals(ConstantRu.BACK) || message.getText().equals(ConstantEn.BACK)) {
                                        currentUser.setState(State.CONTACT);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                        execute(botService.ok(chatId, currentUser.getLanguage()));
                                    } else {
                                        botService.storyWriter(currentUser, message);
                                    }
                                }
                                case LANGUAGE -> {
                                    if (message.getText().equals(ConstantUz.LANGUAGE_ICON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLanguage(Language.UZB);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                    } else if (message.getText().equals(ConstantEn.LANGUAGE_ICON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLanguage(Language.ENG);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                    } else if (message.getText().equals(ConstantRu.LANGUAGE_ICON)) {
                                        currentUser.setState(State.SETTINGS);
                                        currentUser.setLanguage(Language.RUS);
                                        currentUser.setLastOperationTime(LocalDateTime.now());
                                        userRepository.save(currentUser);
                                    } else {
                                        botService.storyWriter(currentUser, message);
                                    }
                                    execute(botService.edited(chatId, currentUser.getLanguage()));
                                }
                                default -> botService.storyWriter(currentUser, message);
                            }
                        }
                    } else if (message.hasContact()) {
                        User user = optionalUser.get();
                        switch (user.getState()) {
                            case START -> {
                                user.setPhone(message.getContact().getPhoneNumber());
                                user.setState(State.CONTACT);
                                user.setLastOperationTime(LocalDateTime.now());
                                userRepository.save(user);
                                execute(botService.menu(chatId, user.getLanguage()));
                            }
                            case SETTINGS -> {
                                user.setPhone(message.getContact().getPhoneNumber());
                                user.setLastOperationTime(LocalDateTime.now());
                                userRepository.save(user);
                                execute(botService.edited(chatId, user.getLanguage()));
                            }
                        }
                    }
                } else if (update.hasCallbackQuery()) {
                    CallbackQuery callbackQuery = update.getCallbackQuery();
                    String chatId = String.valueOf(callbackQuery.getMessage().getChatId());
                    Optional<User> optionalUser = userRepository.findByBot_IdAndChatId(botId, chatId);
                    currentUser = optionalUser.get();
                    switch (currentUser.getState()) {
                        case CONTACT -> {
                            if (callbackQuery.getData().startsWith("$back")) {
                                execute(botService.deleteMessage(chatId, callbackQuery.getMessage().getMessageId()));
                                execute(botService.menu(chatId, currentUser.getLanguage()));
                            } else {
                                currentUser.setState(State.PRODUCT);
                                currentUser.setLastOperationTime(LocalDateTime.now());
                                userRepository.save(currentUser);
                                execute(botService.deleteMessage(chatId, callbackQuery.getMessage().getMessageId()));
                                execute(botService.getProduct(update, currentUser));
                            }
                        }
                        case PRODUCT -> {
                            if (callbackQuery.getData().startsWith("$back")) {
                                currentUser.setState(State.CONTACT);
                                currentUser.setLastOperationTime(LocalDateTime.now());
                                userRepository.save(currentUser);
                                execute(botService.deleteMessage(chatId, callbackQuery.getMessage().getMessageId()));
                                execute(botService.backToProducts(update, chatId, currentUser.getLanguage()));
                            } else if (callbackQuery.getData().startsWith("$request")) {
                                currentUser.setLastOperationTime(LocalDateTime.now());
                                execute(botService.saveRequest(update, currentUser));
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
