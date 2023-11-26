package com.anbara.springboot.aws.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @NotBlank String name, String description,
        @PositiveOrZero BigDecimal price) {


}
