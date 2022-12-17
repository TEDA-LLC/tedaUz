package com.example.tedabot.bot.service;

import com.example.tedabot.model.*;
import com.example.tedabot.repository.*;
import com.example.tedabot.bot.constant.ConstantEn;
import com.example.tedabot.bot.constant.ConstantUz;
import com.example.tedabot.model.enums.Language;
import com.example.tedabot.model.enums.RegisteredType;
import com.example.tedabot.bot.constant.ConstantRu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */

@Service
@RequiredArgsConstructor
public class BotService {
    private final UserHistoryRepository userHistoryRepository;
    private final ButtonService buttonService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final WordHistoryRepository wordHistoryRepository;
    private final RequestRepository requestRepository;
    private final VacancyRepository vacancyRepository;

    public SendMessage start(String chatId) {
        return SendMessage.builder()
                .text(ConstantRu.START +
                        "        \n" +
                        ConstantUz.START +
                        "        \n" +
                        ConstantEn.START)
                .chatId(chatId)
                .replyMarkup(buttonService.language())
                .build();
    }

    public SendMessage language(String chatId, Language language) {
        if (language.equals(Language.RUS)) {
            return SendMessage.builder()
                    .text(ConstantRu.CONTACT)
                    .chatId(chatId)
                    .replyMarkup(buttonService.contact(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CONTACT)
                    .chatId(chatId)
                    .replyMarkup(buttonService.contact(language))
                    .build();
        } else return SendMessage.builder()
                .text(ConstantUz.CONTACT)
                .chatId(chatId)
                .replyMarkup(buttonService.contact(language))
                .build();
    }

    public SendMessage menu(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }
    }

    public SendMessage aboutUs(String chatId, Language language) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode("HTML");
        if (language.equals(Language.UZB))
            sendMessage.setText(ConstantUz.ABOUT_US);
        else if (language.equals(Language.RUS))
            sendMessage.setText(ConstantRu.ABOUT_US);
        else sendMessage.setText(ConstantEn.ABOUT_US);

        sendMessage.setChatId(chatId);

        sendMessage.setReplyMarkup(buttonService.menuButton(language));

        return sendMessage;

    }

    public SendMessage vacancy(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }
    }


    public SendMessage settings(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        }
    }

    public SendMessage edited(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.EDITED)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.EDITED)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else return SendMessage.builder()
                .text(ConstantRu.EDITED)
                .chatId(chatId)
                .replyMarkup(buttonService.settingsMenu(language))
                .build();
    }


    public SendMessage editLanguage(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();

        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();
        }
    }

    public SendMessage ok(String chatId, Language language) {
        return SendMessage.builder()
                .text(ConstantUz.OK)
                .chatId(chatId)
                .replyMarkup(buttonService.menuButton(language))
                .build();
    }

    public SendMessage services(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        }
    }

    public Long getCategoryIdByName(String message) {
        Optional<Category> categoryOptional = categoryRepository.findByNameUzOrNameRuOrNameEn(message, message, message);
        if (categoryOptional.isEmpty()) return null;

        else return categoryOptional.get().getId();
    }

    public SendMessage products(Long categoryId, Language language, String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.products(productRepository.findAllByCategoryId(categoryId), language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        }
    }

    public SendPhoto getProduct(Update update, User currentUser) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));

        Optional<Product> productOptional = productRepository.findById(Long.valueOf(update.getCallbackQuery().getData()));
        Product product = productOptional.get();

        StringBuilder builder = new StringBuilder();
        if (currentUser.getLanguage().equals(Language.UZB)) {
            builder.append(product.getNameUz())
                    .append("\n")
                    .append(product.getDescriptionUz())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        } else if (currentUser.getLanguage().equals(Language.ENG)) {
            builder.append(product.getNameEn())
                    .append("\n")
                    .append(product.getDescriptionEn())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        } else {
            builder.append(product.getNameRu())
                    .append("\n")
                    .append(product.getDescriptionRu())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        }
        UserHistory userHistory = new UserHistory();
        userHistory.setUser(currentUser);
        userHistory.setProduct(product);
        userHistoryRepository.save(userHistory);

        sendPhoto.setCaption(String.valueOf(builder));

        InputFile inputFile = new InputFile(new ByteArrayInputStream(product.getAttachment().getBytes()), product.getAttachment().getOriginalName());
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setReplyMarkup(buttonService.aboutProduct(currentUser.getLanguage(), product));
        return sendPhoto;
    }

    public DeleteMessage deleteMessage(String chatId, Integer messageId) {
        return DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }

    public SendMessage backToProducts(Update update, String chatId, Language language) {
        String data = update.getCallbackQuery().getData();

        Long categoryId = Long.valueOf(data.substring(5));

        List<Product> productList = productRepository.findAllByCategoryId(categoryId);

        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.products(productList, language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        }
    }

    public void storyWriter(User user, Message message) {
        WordHistory wordsHistory = WordHistory.builder().
                user(user).
                word(message.getText()).
                build();
        wordHistoryRepository.save(wordsHistory);
    }

    public SendMessage saveRequest(Update update, User currentUser) {
        String data = update.getCallbackQuery().getData();

        Long productId = Long.valueOf(data.substring(8));
        Optional<Product> productOptional = productRepository.findById(productId);

        Request request = Request.builder().
                aboutProduct(productOptional.get().getNameEn()).
                registeredType(RegisteredType.BOT).
                dateTime(LocalDateTime.now()).
                user(currentUser).
                build();
        Request savedRequest = requestRepository.save(request);

        if (currentUser.getLanguage().equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.RESPONSE_FOR_REQUEST + "\n" +
                            "Sizning murojaat raqamingiz: " + savedRequest.getId())
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .build();
        } else if (currentUser.getLanguage().equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.RESPONSE_FOR_REQUEST + "\n" +
                            "Your reference number: " + savedRequest.getId())
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.RESPONSE_FOR_REQUEST + "\n" +
                            "Ваш номер заявки: " + savedRequest.getId())
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .build();
        }
    }

    public SendMessage myRequests(String chatId, User currentUser) {
        List<Request> requests = requestRepository.findAllByUser(currentUser);

        StringBuilder stringBuilder = new StringBuilder();

        if (requests.isEmpty()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (currentUser.getLanguage().equals(Language.ENG))
                sendMessage.setText(ConstantEn.MY_REQUESTS_EMPTY);
            else if (currentUser.getLanguage().equals(Language.RUS))
                sendMessage.setText(ConstantRu.MY_REQUESTS_EMPTY);
            else
                sendMessage.setText(ConstantUz.MY_REQUESTS_EMPTY);

            return sendMessage;
        }
        for (Request request : requests) {
            if (currentUser.getLanguage().equals(Language.UZB)) {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("Sana: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("Xizmat haqida: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            } else if (currentUser.getLanguage().equals(Language.ENG)) {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("Date: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("About Service: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            } else {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("Дата: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("О проекте: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            }
        }
        return SendMessage.builder()
                .text(String.valueOf(stringBuilder))
                .chatId(chatId)
                .build();
    }

    public SendMessage vacancies(String chatId, User currentUser) {

        List<Vacancy> vacancies = vacancyRepository.findAllByActiveTrue();

        if (vacancies.isEmpty()){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (currentUser.getLanguage().equals(Language.ENG))
                sendMessage.setText(ConstantEn.VACANCY);
            else if (currentUser.getLanguage().equals(Language.RUS))
                sendMessage.setText(ConstantRu.VACANCY);
            else
                sendMessage.setText(ConstantUz.VACANCY);

            return sendMessage;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Vacancy vacancy : vacancies) {
            if (currentUser.getLanguage().equals(Language.UZB)) {
                stringBuilder.append("Id: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("Vakansiya nomi: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("Haqida: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            } else if (currentUser.getLanguage().equals(Language.ENG)) {
                stringBuilder.append("Id: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("Vacancy name: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("About: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            } else {
                stringBuilder.append("Ид: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("Имя вакансия: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("О вакансии: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            }
        }
        return SendMessage.builder()
                .text(String.valueOf(stringBuilder))
                .chatId(chatId)
                .build();
    }
}