package com.anbara.springboot.aws.product;

import com.anbara.springboot.aws.product.dto.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ayoub ANBARA
 */
public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Query(value = """
            select new com.anbara.springboot.aws.product.dto.ProductResponse(p.id,p.sku,p.name,p.description,p.price,p.stockQuantity)
             from Product p 
             where p.sku=:sku 
                """)
    Optional<ProductResponse> findBySku(String sku);

    @Query("""
            select new com.anbara.springboot.aws.product.dto.ProductResponse(p.id,p.sku,p.name,p.description,p.price,p.stockQuantity)
            from Product p 
            where p.sku IN :skus
            """)
    List<ProductResponse> findBySkus(List<String> skus);
}
