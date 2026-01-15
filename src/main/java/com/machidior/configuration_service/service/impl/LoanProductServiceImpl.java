package com.machidior.configuration_service.service.impl;

import com.machidior.configuration_service.dtos.request.LoanProductRequest;
import com.machidior.configuration_service.dtos.response.LoanProductResponse;
import com.machidior.configuration_service.exceptions.BusinessException;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductMapper;
import com.machidior.configuration_service.product.LoanProduct;
import com.machidior.configuration_service.repository.LoanProductRepository;
import com.machidior.configuration_service.service.LoanProductService;
import com.machidior.configuration_service.service.LoanProductVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoanProductServiceImpl implements LoanProductService {

    private final LoanProductRepository productRepository;
    private final LoanProductVersionService versionService;
//    private final SequenceGenerator sequenceGenerator;
    private final LoanProductMapper mapper;

    @Override
    public LoanProductResponse createProduct(LoanProductRequest request) {
        log.info("Creating new loan product: {}", request.getCode());

        if (productRepository.existsByCode(request.getCode())) {
            throw new BusinessException("Product with code " + request.getCode() + " already exists");
        }

        LoanProduct product = mapper.toEntity(request);
        product.setActive(true);

        LoanProduct savedProduct = productRepository.save(product);
        log.debug("Product saved with ID: {}", savedProduct.getId());

        // Create initial DRAFT version
        versionService.createInitialVersion(savedProduct.getId(), request.getCreatedBy());

        return mapper.toResponse(savedProduct);
    }

    @Override
    public LoanProductResponse getProduct(Long productId) {
        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        return mapper.toResponse(product);
    }

    @Override
    public List<LoanProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateProduct(Long productId) {
        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        product.setActive(false);
        productRepository.save(product);
        log.info("Product {} deactivated", productId);
    }

    @Override
    public void activateProduct(Long productId) {
        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        product.setActive(true);
        productRepository.save(product);
        log.info("Product {} activated", productId);
    }

}