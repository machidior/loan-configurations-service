package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.LoanProductChargesRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductChargesMapper;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductCharges;
import com.machidior.configuration_service.repo.LoanProductChargesRepository;
import com.machidior.configuration_service.repo.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanProductChargesService {

    private final LoanProductChargesRepository repository;
    private final LoanProductRepository loanProductRepository;
    private final LoanProductChargesMapper mapper;


    public LoanProductCharges createLoanCharges(LoanProductChargesRequest request) {

        LoanProduct product = loanProductRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan product not found with id: " + request.getProductId()
                        ));

        return repository.save(mapper.toEntity(request,product));
    }

    public LoanProductCharges getLoanProductChargesByProductId(Long productId) {
        LoanProduct loanProduct = loanProductRepository.findById(productId)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Loan Product with id: " + productId + " is not found.")
                );
        return repository.findByLoanProduct(loanProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Charges not defined for product : " + loanProduct.getName()));
    }

    public LoanProductCharges getLoanProductChargesByProductType(LoanProductType productType){
        LoanProduct loanProduct = loanProductRepository.findByProductType(productType)
                .orElseThrow(()->new ResourceNotFoundException("Loan product with the product type " +productType + " is not found."));
        return repository.findByLoanProduct(loanProduct)
                .orElseThrow(()->new ResourceNotFoundException("Charges not defined for product : " + loanProduct.getName()));
    }

    public LoanProductCharges updateProductCharges(Long id, LoanProductChargesRequest request){
        LoanProduct loanProduct = loanProductRepository.findById(request.getProductId())
                .orElseThrow(
                        ()->new ResourceNotFoundException("Loan Product with id: " + request.getProductId() + " is not found.")
                );
        LoanProductCharges charges = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product charges with id " + id + " is not found."));
        charges.setLoanProduct(loanProduct);
        charges.setFirstApplicationFee(request.getFirstApplicationFee());
        charges.setSubsequentApplicationFee(request.getSubsequentApplicationFee());
        charges.setLoanInsurancePercent(request.getLoanInsurancePercent());
        charges.setGroupInsurancePercent(request.getGroupInsurancePercent());
        charges.setExtraRules(request.getExtraRules());
        return repository.save(charges);
    }

    public LoanProductCharges getProductChargesById(Long id){
        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product charges with id " + id + " is not found."));
    }

    public List<LoanProductCharges> getAllCharges() {
        return repository.findAll();
    }

    public void deleteCharges(Long id) {
        repository.deleteById(id);
    }
}