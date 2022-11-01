package com.example.tedabot.service;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.model.Category;
import com.example.tedabot.model.Product;
import com.example.tedabot.model.User;
import com.example.tedabot.model.UserHistory;
import com.example.tedabot.repository.CategoryRepository;
import com.example.tedabot.repository.ProductRepository;
import com.example.tedabot.repository.UserHistoryRepository;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */

@Service
@RequiredArgsConstructor
public class BotService {
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final ButtonService buttonService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SendMessage start(String chatId) {
        return SendMessage.builder()
                .text(ConstantRu.START +
                        "        \n" +
                        ConstantUz.START)
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
        } else return SendMessage.builder()
                .text(ConstantUz.CONTACT)
                .chatId(chatId)
                .replyMarkup(buttonService.contact(language))
                .build();
    }

    public SendMessage contact(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.WELCOME)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.WELCOME)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }

    }

    public SendPhoto aboutUs(String chatId, Language language) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setParseMode("HTML");

        if (language.equals(Language.UZB)) {
            sendPhoto.setCaption(ConstantUz.ABOUT_US);
        } else {
            sendPhoto.setCaption(ConstantRu.ABOUT_US);
        }
        File logo = new File("src/main/resources/img/tedaLOGO.jpg");
        InputFile inputFile = new InputFile(logo);

        sendPhoto.setPhoto(inputFile);
        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(buttonService.menuButton(language));

        return sendPhoto;
    }

    public SendMessage toAdmin(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.TO_ADMIN)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.TO_ADMIN)
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
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        }
    }

    public Long getCategoryId(String message) {
        Optional<Category> categoryOptional = categoryRepository.findByNameRuOrNameUz(message, message);
        if (categoryOptional.isEmpty()) return null;

        else return categoryOptional.get().getId();
    }

    public SendMessage products(Long categoryId, Language language, String chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.productButtons(productRepository.findAllByCategoryId(categoryId), language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
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
        sendPhoto.setReplyMarkup(buttonService.backButton(currentUser.getLanguage(), product));
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


        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.productButtons(productList, language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
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
}
