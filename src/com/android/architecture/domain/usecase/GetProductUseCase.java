package com.android.architecture.domain.usecase;

import com.android.architecture.repository.ProductRepository;
import java.util.List;

/**
 * Use Case for retrieving products.
 */
public class GetProductUseCase {
    private ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Object> execute(String category) {
        List<Object> products = productRepository.getAllProducts();
        // Business logic: filter by category, apply sorting, etc.
        return products;
    }
}
