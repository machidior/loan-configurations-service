package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.request.LoanProductRequest;
import com.machidior.configuration_service.dtos.response.LoanProductResponse;

import java.util.List;

public interface LoanProductService {
    LoanProductResponse createProduct(LoanProductRequest request);

    LoanProductResponse getProduct(Long productId);

    List<LoanProductResponse> getAllProducts();

    void deactivateProduct(Long productId);

    void activateProduct(Long productId);
}
