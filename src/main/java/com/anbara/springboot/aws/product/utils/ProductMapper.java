package com.anbara.springboot.aws.product.utils;

import com.anbara.springboot.aws.product.Product;
import com.anbara.springboot.aws.product.dto.NewProductRequest;
import com.anbara.springboot.aws.product.dto.ProductResponse;

/**
 * @author Ayoub ANBARA
 */
public class ProductMapper {

    public static Product newProductDtoToProduct(NewProductRequest newProductRequest) {
        // we can use this mapper to map dto to entity
        // or we can use external libraries like JMapper
        // or we can use beanutils.copyproperties from spring
        var product = new Product();
        product.setName(newProductRequest.name());
        product.setSku(newProductRequest.sku());
        product.setDescription(newProductRequest.description());
        product.setPrice(newProductRequest.price());
        return product;
    }

    public static ProductResponse productEntityToProductResponse(Product p) {
        return new ProductResponse(p.getId(), p.getSku(), p.getName(),
                p.getDescription(), p.getPrice(), p.getStockQuantity());
    }
}
