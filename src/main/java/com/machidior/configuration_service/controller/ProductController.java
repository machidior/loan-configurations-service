package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.request.LoanProductRequest;
import com.machidior.configuration_service.dtos.response.LoanProductResponse;
import com.machidior.configuration_service.product.LoanProduct;
import com.machidior.configuration_service.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final LoanProductService productService;

    @PostMapping("/create")
    public ResponseEntity<LoanProductResponse> createProduct(@RequestBody LoanProductRequest request) {
        String createdBy = "system_user"; // BUG: retrieve this from the authenticated user context
        request.setCreatedBy(createdBy);
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<LoanProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/deactivate/{productId}")
    public ResponseEntity<?> deactivateProduct(@PathVariable Long productId) {
        productService.deactivateProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activate/{productId}")
    public ResponseEntity<?> activateProduct(@PathVariable Long productId) {
        productService.activateProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
