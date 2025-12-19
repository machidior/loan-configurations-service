package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.LoanProductRequirementsRequest;
import com.machidior.configuration_service.dtos.LoanProductRequirementsUpdateRequest;
import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductRequirementsMapper;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductRequirements;
import com.machidior.configuration_service.repo.LoanProductRepository;
import com.machidior.configuration_service.repo.LoanProductRequirementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanProductRequirementsService {

    private final LoanProductRequirementsRepository repository;
    private final LoanProductRepository loanProductRepository;
    private final LoanProductRequirementsMapper mapper;

    public LoanProductRequirements createProductRequirements(LoanProductRequirementsRequest request) {
        LoanProduct loanProduct = loanProductRepository.findById(request.getProductId())
                .orElseThrow(()->new ResourceNotFoundException("Loan product not found. Id: " + request.getProductId()));
        return repository.save(mapper.toEntity(request,loanProduct));
    }

    public LoanProductRequirements getLoanProductRequirements(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No loan product requirements found with id " + id));
    }

    public LoanProductRequirements getLoanProductRequirementsByProductType(LoanProductType productType){
        LoanProduct product = loanProductRepository.findByProductType(productType)
                .orElseThrow(()->new ResourceNotFoundException("No loan Product with product type: " + productType));
        return repository.findByLoanProduct(product)
                .orElseThrow(()->new ResourceNotFoundException("No loan product requirements created for loan product: " + product.getName()));
    }

    public LoanProductRequirements getLoanProductRequirementsByProductId(Long productId){
        LoanProduct product = loanProductRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("No loan Product with product id: " + productId));
        return repository.findByLoanProduct(product)
                .orElseThrow(()->new ResourceNotFoundException("No loan product requirements created for loan product: " + product.getName()));
    }

    public LoanProductRequirements updateLoanProductRequirements(Long id, LoanProductRequirementsUpdateRequest request) {
        LoanProductRequirements requirements = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No loan product requirements found with id " + id));
        requirements.setRequiresGuarantor(request.getRequiresGuarantor());
        requirements.setMinGuarantors(request.getMinGuarantors());
        requirements.setRequiresCollateral(request.getRequiresCollateral());
        requirements.setAllowedCollateralTypes(parseCollateralTypes(request.getAllowedCollateralTypes()));

        return repository.save(requirements);

    }

    private List<CollateralType> parseCollateralTypes(List<String> types){
        try {
            List<CollateralType> collateralTypes = null;
            for (String type: types){
                collateralTypes.add(CollateralType.valueOf(type));
            }
            return collateralTypes;
        } catch ( InvalidEnumException e){
            throw new InvalidEnumException("Invalid Collateral type.");
        }
    }
}
