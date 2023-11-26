package com.anbara.springboot.aws.config;

import com.anbara.springboot.aws.product.ProductService;
import com.anbara.springboot.aws.product.dto.NewProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Ayoub ANBARA
 */
@Component
@ConditionalOnProperty(name = "init-db",havingValue = "true")
@RequiredArgsConstructor
public class DataInitializer {
    private final ProductService productService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            var p1 = new NewProductRequest("sk1", "name1", "desc1", BigDecimal.valueOf(150),55);
            var p2 = new NewProductRequest("sk2", "name2", "desc2", BigDecimal.valueOf(750),5);
            var p3 = new NewProductRequest("sk3", "name3", "desc3", BigDecimal.valueOf(100),0);
            productService.createProduct(p1);
            productService.createProduct(p2);
            productService.createProduct(p3);
        };
    }
}
