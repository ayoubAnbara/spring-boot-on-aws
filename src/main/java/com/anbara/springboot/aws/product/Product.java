package com.anbara.springboot.aws.product;

import com.anbara.springboot.aws.audit.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Ayoub ANBARA
 */
@Entity
@Table
@Getter
@Setter
public class Product extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String sku;
    private String name;
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @PositiveOrZero
    private int stockQuantity;


}
