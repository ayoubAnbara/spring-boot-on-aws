package com.anbara.springboot.aws.product;

import com.anbara.springboot.aws.product.dto.NewProductRequest;
import com.anbara.springboot.aws.product.dto.ProductResponse;
import com.anbara.springboot.aws.product.dto.UpdateProductRequest;
import com.anbara.springboot.aws.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author Ayoub ANBARA
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{sku}")
    ResponseEntity<ProductResponse> getProductBySku(@PathVariable(name = "sku") String sku) {
        var productResponse = productService.findProductBySku(sku);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<ProductResponse>> getProductsBySkus(@RequestParam(name = "skus") List<String> skus) {
        log.trace("method called bySkus with args: skus={}",skus);
        var productList = productService.findProductsBySkus(skus);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid NewProductRequest newProduct) {
        log.debug("endpoint invoked createProduct with body: {}", newProduct);
        ProductResponse productCreated = productService.createProduct(newProduct);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id,
                                                  @RequestBody @Valid UpdateProductRequest updateProduct) {
        log.debug("endpoint invoked updateProduct with body: {}", updateProduct);
        ProductResponse productUpdated = productService.updateProduct(id, updateProduct);
        return new ResponseEntity<>(productUpdated, HttpStatus.NO_CONTENT);
    }

}
