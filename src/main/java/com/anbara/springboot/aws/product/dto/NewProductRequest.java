package com.anbara.springboot.aws.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NewProductRequest(
        @NotBlank String sku,
        @NotBlank String name, String description,
        @Schema(name = "Product price", example = "$100.00", required = true)
        @PositiveOrZero BigDecimal price,
        @Schema(name = "stockQuantity", example = "should be positive or zero")
        @PositiveOrZero int stockQuantity) {

    @Override
    public String toString() {
        return "NewProductDto{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
