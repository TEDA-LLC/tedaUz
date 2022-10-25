package com.example.tedabot.service;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
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
        SendMessage sendMessage = new SendMessage();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();


        if (language.equals(Language.UZB)) {
            button.setText(ConstantUz.ABOUT_US_BUTTON);
            button1.setText(ConstantUz.SERVICES_BUTTON);
            button2.setText(ConstantUz.SETTINGS_BUTTON);
            button3.setText(ConstantUz.TO_ADMIN_BUTTON);

            sendMessage.setText(ConstantUz.WELCOME);
        } else {
            button.setText(ConstantRu.ABOUT_US_BUTTON);
            button1.setText(ConstantRu.SERVICES_BUTTON);
            button2.setText(ConstantRu.SETTINGS_BUTTON);
            button3.setText(ConstantRu.TO_ADMIN_BUTTON);

            sendMessage.setText(ConstantRu.WELCOME);
        }
        row.add(button);
        row.add(button1);
        row1.add(button2);
        row1.add(button3);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
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

        return sendPhoto;
    }

    public SendMessage toAdmin(Update update, Language language) {
        SendMessage sendMessage = new SendMessage();
        if (language.equals(Language.UZB)) {
            sendMessage.setText(ConstantUz.TO_ADMIN);
        } else {
            sendMessage.setText(ConstantRu.TO_ADMIN);
        }
        sendMessage.setChatId(update.getMessage().getChatId());
        return sendMessage;
    }


    public SendMessage settings(Update update, Language languageEnum) {
        SendMessage sendMessage = new SendMessage();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton language = new KeyboardButton();
        KeyboardButton number = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();

        number.setRequestContact(true);


        if (languageEnum.equals(Language.UZB)) {
            language.setText(ConstantUz.LANGUAGE);
            number.setText(ConstantUz.PHONE);
            back.setText(ConstantUz.BACK);

            sendMessage.setText(ConstantUz.CHOOSE);
        } else {
            language.setText(ConstantRu.LANGUAGE);
            number.setText(ConstantRu.PHONE);
            back.setText(ConstantRu.BACK);

            sendMessage.setText(ConstantRu.CHOOSE);
        }

        row.add(language);
        row.add(number);
        row1.add(back);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }

    public SendMessage editContact(Update update, Language language) {
        if (language.equals(Language.UZB)){
            return SendMessage.builder()
                    .text(ConstantUz.EDITED)
                    .chatId(update.getMessage().getChatId())
                    .build();
        }
        else return SendMessage.builder()
                .text(ConstantRu.EDITED)
                .chatId(update.getMessage().getChatId())
                .build();
    }


}
