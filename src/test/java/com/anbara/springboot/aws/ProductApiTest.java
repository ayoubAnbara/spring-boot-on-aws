package com.anbara.springboot.aws;

import com.anbara.springboot.aws.product.ProductController;
import com.anbara.springboot.aws.product.ProductService;
import com.anbara.springboot.aws.product.dto.ProductResponse;
import com.anbara.springboot.aws.product.dto.UpdateProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ayoub ANBARA
 */
@WebMvcTest(ProductController.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class ProductApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @Test
    void testGetListPoductsBySkuWhereProductsExistInDB() throws Exception {
        UUID id = UUID.randomUUID();
        var sku = "sk1";
        var name = "laptop X";
        var description = "Gen 3, Ram 30 gb";
        var price = BigDecimal.valueOf(1000);
        int stockQuantity = 50;
        var fakeProductResponse = new ProductResponse(id, sku, name, description, price, stockQuantity);

        when(productService.findProductBySku(sku)).thenReturn(fakeProductResponse);

        mockMvc.perform(get("/api/v1/products/" + sku))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.sku").value(sku))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.stockQuantity").value(stockQuantity));
    }
    @Test
    void testUpdateWithExistingProduct() throws Exception {
        UUID id = UUID.randomUUID();
        var sku = "sk1";
        var name = "new product name";
        var description = "new description";
        var price = BigDecimal.valueOf(9000);
        var updateProductRequest = new UpdateProductRequest(name, description, price);
        int stockQuantity = 50;

        var fakeProductResponse = new ProductResponse(id, sku, name, description, price, stockQuantity);

        when(productService.updateProduct(id, updateProductRequest)).thenReturn(fakeProductResponse);

        mockMvc.perform(
                        patch("/api/v1/products/" + id)
                                .content(objectMapper.writeValueAsBytes(updateProductRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(fakeProductResponse.id().toString()))
                .andExpect(jsonPath("$.sku").value(fakeProductResponse.sku()))
                .andExpect(jsonPath("$.name").value(fakeProductResponse.name()))
                .andExpect(jsonPath("$.description").value(fakeProductResponse.description()))
                .andExpect(jsonPath("$.price").value(fakeProductResponse.price()))
                .andExpect(jsonPath("$.stockQuantity").value(fakeProductResponse.stockQuantity()));
    }

// todo PS: I should add Positive Testing vs Negative Testing like testUpdateWithNonExistingProduct

}
