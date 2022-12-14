package com.example.tedabot.component;

import com.example.tedabot.model.Bot;
import com.example.tedabot.model.Category;
import com.example.tedabot.repository.BotRepository;
import com.example.tedabot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:11 *
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final BotRepository botRepository;
    @Value("${spring.sql.init.mode}")
    String mode;
    @Override
    public void run(String... args) {
        if (mode.equals("always")) {
            Category service = Category.builder()
                    .nameUz("Xizmatlar \uD83D\uDEE0")
                    .nameRu("Услуги \uD83D\uDEE0")
                    .nameEn("Services \uD83D\uDEE0")
                    .build();
            Category system = Category.builder()
                    .nameUz("Tizimlar \uD83D\uDCBB")
                    .nameRu("Системы \uD83D\uDCBB")
                    .nameEn("Systems \uD83D\uDCBB")
                    .build();
            categoryRepository.save(service);
            categoryRepository.save(system);
            Bot bot = new Bot();
            bot.setToken("5432072116:AAHHjQHDP-IBzzQdiRyzHhqValr5tKQ6tlI");
            bot.setUsername("tedauz_bot");
            botRepository.save(bot);

        }
    }
}