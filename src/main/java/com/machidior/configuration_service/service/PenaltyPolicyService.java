package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.PenaltyPolicyRequest;
import com.machidior.configuration_service.dtos.PenaltyPolicyUpdateRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.enums.PenaltyCalculationType;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.PenaltyPolicyMapper;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.PenaltyPolicy;
import com.machidior.configuration_service.repo.PenaltyPolicyRepository;
import com.machidior.configuration_service.repo.LoanProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PenaltyPolicyService {

    private final PenaltyPolicyRepository repository;
    private final LoanProductRepository loanProductRepository;
    private final PenaltyPolicyMapper mapper;

    public PenaltyPolicy createPenaltyPolicy(PenaltyPolicyRequest request){
        LoanProduct product = loanProductRepository
                .findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan product not found with id: " + request.getProductId()
                        ));
        return repository.save(mapper.toEntity(request,product));
    }

    public PenaltyPolicy getPenaltyPolicy(Long id){
        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Loan product penalty policy not set. ID: " + id));
    }

    public PenaltyPolicy getPenaltyPolicyByProductType(LoanProductType productType){
        LoanProduct product = loanProductRepository.findByProductType(productType)
                .orElseThrow(()->new ResourceNotFoundException("No loan Product with product type: " + productType));
        return repository.findByLoanProduct(product)
                .orElseThrow(()->new ResourceNotFoundException("No penalty policy created for loan product: " + product.getName()));
    }

    public PenaltyPolicy getPenaltyPolicyByProductId(Long productId){
        LoanProduct product = loanProductRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("No loan Product with product id: " + productId));
        return repository.findByLoanProduct(product)
                .orElseThrow(()->new ResourceNotFoundException("No penalty policy created for loan product: " + product.getName()));
    }

    public PenaltyPolicy updatePenaltyPolicy(Long id, PenaltyPolicyUpdateRequest request){
        PenaltyPolicy policy = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No penalty policy found with id: "));
        policy.setLatePenaltyRate(request.getLatePenaltyRate());
        policy.setGracePeriodDays(request.getGracePeriodDays());
        policy.setCalculationType(PenaltyCalculationType.valueOf(request.getCalculationType()));
        return repository.save(policy);
    }
}
