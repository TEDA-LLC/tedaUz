package com.example.tedabot.service;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import com.example.tedabot.constant.enums.State;
import com.example.tedabot.model.User;
import com.example.tedabot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */

@Service
@RequiredArgsConstructor
public class BotService {
    private final UserRepository userRepository;

    public SendMessage welcome(Update update) {
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

        if (language.equals(Language.RUS)){
            return SendMessage.builder()
                    .text(ConstantRu.CONTACT)
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(replyKeyboardMarkup)
                    .build();
        }
        else return SendMessage.builder()
                .text(ConstantUz.CONTACT)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(replyKeyboardMarkup)
                .build();

    }
}
