package com.example.lab_6.service;

import com.example.lab_6.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(int id, Product updatedProduct) {
        Optional<Product> productOptional = getProductById(id);
        productOptional.ifPresent(product -> {
            product.setTitle(updatedProduct.getTitle());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
        });
        return productOptional;
    }

    public boolean deleteProduct(int id) {
        return products.removeIf(product -> product.getId() == id);
    }
}
