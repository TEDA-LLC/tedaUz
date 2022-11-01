package com.example.tedabot.component;

import com.example.tedabot.model.Category;
import com.example.tedabot.model.Product;
import com.example.tedabot.repository.CategoryRepository;
import com.example.tedabot.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")){
            Category service = Category.builder()
                    .nameUz("Xizmatlar⚙️")
                    .nameRu("Услуги⚙️")
                    .build();

            Category system = Category.builder()
                    .nameUz("Tizimlar\uD83D\uDCBB")
                    .nameRu("Системы\uD83D\uDCBB")
                    .build();
            Category serviceSaved = categoryRepository.save(service);
            Category systemSaved = categoryRepository.save(system);


//            Product site = Product.builder()
//                    .nameUz("Web site")
//                    .nameRu("Сайты")
//                    .category(serviceSaved)
//                    .build();
//
//            Product mobile = Product.builder()
//                    .nameUz("Mobile apps")
//                    .nameRu("Приложение")
//                    .category(serviceSaved)
//                    .build();
//
//            Product bot = Product.builder()
//                    .nameUz("TelegramBot")
//                    .nameRu("Телеграм Бот")
//                    .category(serviceSaved)
//                    .build();
//
//
//            Product pMPro = Product.builder()
//                    .nameUz("Profit Model Pro")
//                    .nameRu("Пм Про")
//                    .category(systemSaved)
//                    .build();
//
//            Product event = Product.builder()
//                    .nameUz("Event")
//                    .nameRu("Ивент")
//                    .category(systemSaved)
//                    .build();
//
//            productRepository.save(site);
//            productRepository.save(mobile);
//            productRepository.save(bot);
//            productRepository.save(pMPro);
//            productRepository.save(event);

        }
    }
}
