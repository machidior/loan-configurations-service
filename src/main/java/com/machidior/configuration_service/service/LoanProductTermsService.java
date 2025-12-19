package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.LoanProductTermsRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductTermsMapper;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductTerms;
import com.machidior.configuration_service.repo.LoanProductRepository;
import com.machidior.configuration_service.repo.LoanProductTermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanProductTermsService {

    private final LoanProductTermsRepository repository;
    private final LoanProductRepository loanProductRepository;
    private final LoanProductTermsMapper mapper;


    public LoanProductTerms createLoanProductTerms(LoanProductTermsRequest request) {
        LoanProduct loanProduct = loanProductRepository.findById(request.getProductId())
                        .orElseThrow(()->new ResourceNotFoundException("Loan product with id " + request.getProductId() + " is not found."));

        LoanProductTerms terms = mapper.toEntity(request,loanProduct);
        validateTerms(terms);
        return repository.save(terms);
    }

    public LoanProductTerms updateTerms(Long id, LoanProductTermsRequest request) {
        LoanProductTerms existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terms not found with ID: " + id));
        LoanProduct loanProduct = loanProductRepository.findById(request.getProductId())
                        .orElseThrow(()->new ResourceNotFoundException("Loan product with id " + request.getProductId() + " is not found"));

        existing.setLoanProduct(loanProduct);
        existing.setMinAmount(request.getMinAmount());
        existing.setMaxAmount(request.getMaxAmount());
        existing.setMaximumTermMonths(request.getMaximumTermMonths());
        existing.setMonthlyLoanFeeRate(request.getMonthlyLoanFeeRate());
        existing.setLoanFeeRatePer2Months(request.getLoanFeeRatePer2Months());
        existing.setMonthlyInterestRate(request.getMonthlyInterestRate());
        existing.setExtraRules(request.getExtraRules());
        existing.setMinGroupMembers(request.getMinGroupMembers());
        existing.setMaxGroupMembers(request.getMaxGroupMembers());

        validateTerms(existing);
        return repository.save(existing);
    }

    public LoanProductTerms getTermsByProductType(LoanProductType productType) {
        LoanProduct loanProduct = loanProductRepository.findByProductType(productType)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with the product type " +productType + " is not found."));
        return repository.findByLoanProduct(loanProduct)
                .orElseThrow(() -> new RuntimeException("Loan product terms not found for type: " + productType));
    }

    public List<LoanProductTerms> getAllTerms() {
        return repository.findAll();
    }

    public void deleteTerms(Long id) {
        repository.deleteById(id);
    }

    public List<LoanProductTerms> findByAmountRange(BigDecimal min, BigDecimal max) {
        return repository.findAll().stream()
                .filter(t -> (min == null || t.getMinAmount().compareTo(min) >= 0) &&
                        (max == null || t.getMaxAmount().compareTo(max) <= 0))
                .toList();
    }

    private void validateTerms(LoanProductTerms terms) {


        if (terms.getLoanProduct().getProductType().name().equals("GROUP_PRODUCT")) {
            if (terms.getMinGroupMembers() == null || terms.getMaxGroupMembers() == null)
                throw new IllegalArgumentException("Group products must specify min and max group members.");
        }

        if (terms.getMinAmount() == null || terms.getMaxAmount() == null)
            throw new IllegalArgumentException("Amount range must be defined.");
    }
}
