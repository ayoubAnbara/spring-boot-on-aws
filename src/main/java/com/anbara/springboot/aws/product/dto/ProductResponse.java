package com.anbara.springboot.aws.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ayoub ANBARA
 */
public record ProductResponse(
        UUID id, String sku,
        String name, String description,
        BigDecimal price, int stockQuantity
) {

}
