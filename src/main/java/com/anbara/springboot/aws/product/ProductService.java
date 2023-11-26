package com.anbara.springboot.aws.product;

import com.anbara.springboot.aws.product.dto.NewProductRequest;
import com.anbara.springboot.aws.product.dto.ProductResponse;
import com.anbara.springboot.aws.product.dto.UpdateProductRequest;
import com.anbara.springboot.aws.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.anbara.springboot.aws.product.utils.ProductMapper.newProductDtoToProduct;
import static com.anbara.springboot.aws.product.utils.ProductMapper.productEntityToProductResponse;

/**
 * @author Ayoub ANBARA
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public ProductResponse findProductBySku(String sku) {
        Optional<ProductResponse> optionalProduct = productRepo.findBySku(sku);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with sku=%s not found".formatted(sku)); // i18N
        }
        return optionalProduct.get();
    }

    // todo: it is better to use pagination
    public List<ProductResponse> findProductsBySkus(List<String> skus) {
        return productRepo.findBySkus(skus);
    }

    @Transactional
    public ProductResponse createProduct(NewProductRequest newProductRequest) {
        Product product = newProductDtoToProduct(newProductRequest);
        var productSaved = productRepo.save(product);
        return productEntityToProductResponse(productSaved);
    }

    @Transactional
    public ProductResponse updateProduct(UUID id, UpdateProductRequest updateProductRequest) {
        Product productEntity = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product with id=" + id + " not found"));

        // todo  separate this logic into a method to map UpdateProductDto to entity
        productEntity.setName(updateProductRequest.name());
        productEntity.setDescription(updateProductRequest.description());
        productEntity.setPrice(updateProductRequest.price());
        Product productSaved = productRepo.save(productEntity);

        return productEntityToProductResponse(productSaved);
    }
}
