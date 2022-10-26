package com.example.tedabot.service;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */

@Service
@RequiredArgsConstructor
public class BotService {
    private final UserRepository userRepository;
    private final ButtonService buttonService;

    public SendMessage start(Update update) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton rus = new KeyboardButton();
        KeyboardButton uzb = new KeyboardButton();
        rus.setText(ConstantRu.BUTTON);
        uzb.setText(ConstantUz.BUTTON);
        row.add(rus);
        row.add(uzb);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        return SendMessage.builder()
                .text(ConstantRu.START +
                        "        \n" +
                        ConstantUz.START)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(replyKeyboardMarkup)
                .build();
    }

    public SendMessage language(Update update, Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setRequestContact(true);

        if (language.equals(Language.RUS)) {
            button.setText(ConstantRu.CONTACT_BUTTON);
        } else button.setText(ConstantUz.CONTACT_BUTTON);

        row.add(button);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        if (language.equals(Language.RUS)) {
            return SendMessage.builder()
                    .text(ConstantRu.CONTACT)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(replyKeyboardMarkup)
                    .build();
        } else return SendMessage.builder()
                .text(ConstantUz.CONTACT)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(replyKeyboardMarkup)
                .build();
    }

    public SendMessage contact(Update update, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.WELCOME)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.WELCOME)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }

    }

    public SendPhoto aboutUs(Update update, Language language) {
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
        sendPhoto.setChatId(update.getMessage().getChatId());
        sendPhoto.setReplyMarkup(buttonService.menuButton(language));

        return sendPhoto;
    }

    public SendMessage toAdmin(Update update, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.TO_ADMIN)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.TO_ADMIN)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }
    }


    public SendMessage settings(Update update, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        }
    }

    public SendMessage edited(Update update, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.EDITED)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else return SendMessage.builder()
                .text(ConstantRu.EDITED)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(buttonService.settingsMenu(language))
                .build();
    }


    public SendMessage editLanguage(Update update, Language language) {
        SendMessage sendMessage = new SendMessage();
        if (language.equals(Language.UZB)) {
            sendMessage.setText(ConstantUz.CHOOSE_LANGUAGE);
        } else {
            sendMessage.setText(ConstantRu.CHOOSE_LANGUAGE);
        }

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton rus = new KeyboardButton();
        KeyboardButton uzb = new KeyboardButton();
        rus.setText(ConstantRu.LANGUAGE_ICON);
        uzb.setText(ConstantUz.LANGUAGE_ICON);
        row.add(rus);
        row.add(uzb);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
