package com.example.tedabot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 10:36 *
 */

public interface BotService {

    SendMessage start(String chatId, Update update);

}
