package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.LoanProductRequest;
import com.machidior.configuration_service.dtos.LoanProductUpdateRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.ResourceDuplicateException;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductMapper;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.repo.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanProductService {

    private final LoanProductRepository loanProductRepository;
    private final LoanProductMapper mapper;

    public LoanProduct createLoanProduct(LoanProductRequest request) {

        if (loanProductRepository.existsByProductType(LoanProductType.valueOf(request.getProductType()))) {
            throw new ResourceDuplicateException(
                    "Loan product already exists for productType: " + request.getProductType()
            );
        }
        return loanProductRepository.save(mapper.toEntity(request));
    }

    public List<LoanProduct> getAllLoanProducts(){
        return loanProductRepository.findAll();
    }

    public LoanProduct getLoanProductById(Long id){
        return loanProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with id - " + id + " is not found."));
    }

    public LoanProduct getLoanProductByProductType(LoanProductType productType) {
        return loanProductRepository.findByProductType(productType)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with product type " + productType + " is not found"));
    }

    public void activateLoanProduct(Long id){
        LoanProduct loanProduct = loanProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with id " + id + " is not found."));
        loanProduct.setActive(true);
        loanProductRepository.save(loanProduct);
    }

    public void deActivateLoanProduct(Long id){
        LoanProduct loanProduct = loanProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with id " + id + " is not found."));
        loanProduct.setActive(false);
        loanProductRepository.save(loanProduct);
    }

    public LoanProduct updateProductDetails(Long id, LoanProductUpdateRequest request){
        LoanProduct loanProduct = loanProductRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with id " + id + " is not found."));

        loanProduct.setName(request.getName());
        loanProduct.setDescription(request.getDescription());
        return loanProductRepository.save(loanProduct);
    }
}
