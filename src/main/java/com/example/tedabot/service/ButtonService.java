package com.example.tedabot.service;

import com.example.tedabot.constant.ConstantRu;
import com.example.tedabot.constant.ConstantUz;
import com.example.tedabot.constant.enums.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:30 *
 */

@Service
@RequiredArgsConstructor
public class ButtonService {

    public ReplyKeyboardMarkup menuButton(Language language) {
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
        } else {
            button.setText(ConstantRu.ABOUT_US_BUTTON);
            button1.setText(ConstantRu.SERVICES_BUTTON);
            button2.setText(ConstantRu.SETTINGS_BUTTON);
            button3.setText(ConstantRu.TO_ADMIN_BUTTON);
        }
        row.add(button);
        row.add(button1);
        row1.add(button2);
        row1.add(button3);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup settingsMenu(Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton languageButton = new KeyboardButton();
        KeyboardButton number = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();

        number.setRequestContact(true);


        if (language.equals(Language.UZB)) {
            languageButton.setText(ConstantUz.LANGUAGE);
            number.setText(ConstantUz.PHONE);
            back.setText(ConstantUz.BACK);
        } else {
            languageButton.setText(ConstantRu.LANGUAGE);
            number.setText(ConstantRu.PHONE);
            back.setText(ConstantRu.BACK);
        }

        row.add(languageButton);
        row.add(number);
        row1.add(back);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

}
