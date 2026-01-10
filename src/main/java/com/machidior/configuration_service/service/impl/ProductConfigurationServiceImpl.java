package com.machidior.configuration_service.service.impl;

import com.machidior.configuration_service.dtos.request.charges.ProductChargeRequest;
import com.machidior.configuration_service.dtos.request.policy.PoliciesRequest;
import com.machidior.configuration_service.dtos.request.requirement.RequirementsRequests;
import com.machidior.configuration_service.dtos.response.ConfigurationsResponse;
import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.dtos.response.policy.PoliciesResponse;
import com.machidior.configuration_service.dtos.response.requirement.RequirementsResponse;
import com.machidior.configuration_service.enums.DisbursementMethod;
import com.machidior.configuration_service.enums.InstallmentFrequency;
import com.machidior.configuration_service.enums.TenureUnit;
import com.machidior.configuration_service.enums.VersionStatus;
import com.machidior.configuration_service.exceptions.ImmutableVersionException;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.*;
import com.machidior.configuration_service.product.charge.ProductCharge;
import com.machidior.configuration_service.product.policy.*;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.LoanProductVersionRepository;
import com.machidior.configuration_service.repository.*;
import com.machidior.configuration_service.service.ProductConfigurationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductConfigurationServiceImpl implements ProductConfigurationService {

    private final LoanProductVersionRepository versionRepository;
    private final ProductEligibilityRepository eligibilityPolicyRepository;
    private final ProductTermsRepository termsPolicyRepository;
    private final InterestPolicyRepository interestPolicyRepository;
    private final PenaltyPolicyRepository penaltyPolicyRepository;
    private final DefaultPolicyRepository defaultPolicyRepository;
    private final ProductChargeRepository chargeRepository;
    private final ProductChargeMapper chargeMapper;
    private final InterestPolicyMapper interestPolicyMapper;
    private final PenaltyPolicyMapper penaltyPolicyMapper;
    private final ProductEligibilityMapper productEligibilityMapper;
    private final DefaultPolicyMapper defaultPolicyMapper;
    private final ProductTermsMapper termsPolicyMapper;


    private final SolidarityRequirementRepository solidarityRequirementRepository;
    private final AgricultureRequirementRepository agricultureRequirementRepository;
    private final AssetRequirementRepository assetRequirementRepository;
    private final CollateralRequirementRepository collateralRequirementRepository;
    private final GuarantorRequirementRepository guarantorRequirementRepository;
    private final EmploymentRequirementRepository employmentRequirementRepository;
    private final BusinessRequirementRepository businessRequirementRepository;
    private final DigitalConsentRequirementRepository digitalConsentRequirementRepository;
    private final EducationRequirementRepository educationRequirementRepository;
    private final FinancialHistoryRequirementRepository financialHistoryRequirementRepository;
    private final HousingRequirementRepository housingRequirementRepository;

    private final AgricultureRequirementMapper agricultureRequirementMapper;
    private final SolidarityRequirementMapper solidarityRequirementMapper;
    private final AssetRequirementMapper assetRequirementMapper;
    private final CollateralRequirementMapper collateralRequirementMapper;
    private final GuarantorRequirementMapper guarantorRequirementMapper;
    private final EmploymentRequirementMapper employmentRequirementMapper;
    private final BusinessRequirementMapper businessRequirementMapper;
    private final DigitalConsentRequirementMapper digitalConsentRequirementMapper;
    private final EducationRequirementMapper educationRequirementMapper;
    private final FinancialHistoryRequirementMapper financialHistoryRequirementMapper;
    private final HousingRequirementMapper housingRequirementMapper;

    @Override
    public PoliciesResponse updatePolicies(Long versionId, PoliciesRequest policies) {
        log.info("Updating policies for version: {}", versionId);

        LoanProductVersion version = validateVersionForConfigurationUpdate(versionId);

        DefaultPolicy defaultPolicy = null;
        InterestPolicy interestPolicy = null;
        PenaltyPolicy penaltyPolicy = null;
        ProductEligibility eligibilityPolicy = null;
        ProductTerms termsPolicy = null;

        if (policies.getDefaultPolicy() != null) {
            DefaultPolicy policy = defaultPolicyRepository.findByProductVersion(version)
                    .orElse(new DefaultPolicy());
            policy.setProductVersion(version);
            policy.setDaysOverdueToDefault(policies.getDefaultPolicy().getDaysOverdueToDefault());
            policy.setFreezeInterestAccrual(policies.getDefaultPolicy().getFreezeInterestAccrual());
            policy.setFreezePenaltyAccrual(policies.getDefaultPolicy().getFreezePenaltyAccrual());
            policy.setBlockFurtherDisbursement(policies.getDefaultPolicy().getBlockFurtherDisbursement());
            policy.setMarkAsNonPerforming(policies.getDefaultPolicy().getMarkAsNonPerforming());
            policy.setBlockIfAnyDefaultedLoan(policies.getDefaultPolicy().getBlockIfAnyDefaultedLoan());
            defaultPolicy = defaultPolicyRepository.save(policy);
        }

        if (policies.getTermsPolicy() != null) {
            ProductTerms terms = termsPolicyRepository.findByProductVersion(version)
                    .orElse(new ProductTerms());
            terms.setProductVersion(version);
            terms.setMinLoanAmount(policies.getTermsPolicy().getMinLoanAmount());
            terms.setMaxLoanAmount(policies.getTermsPolicy().getMaxLoanAmount());
            terms.setFirstTimeBorrowerMaxAmount(policies.getTermsPolicy().getFirstTimeBorrowerMaxAmount());
            terms.setMinTenure(policies.getTermsPolicy().getMinTenure());
            terms.setMaxTenure(policies.getTermsPolicy().getMaxTenure());
            terms.setTenureUnit(policies.getTermsPolicy().getTenureUnit() != null ?
                    TenureUnit.valueOf(policies.getTermsPolicy().getTenureUnit()) : terms.getTenureUnit());
            terms.setInstallmentFrequency(policies.getTermsPolicy().getInstallmentFrequency() != null ?
                    InstallmentFrequency.valueOf(policies.getTermsPolicy().getInstallmentFrequency()) : terms.getInstallmentFrequency());
            terms.setMinInstallments(policies.getTermsPolicy().getMinInstallments());
            terms.setMaxInstallments(policies.getTermsPolicy().getMaxInstallments());
            terms.setRepaymentDayOfTheMonth(policies.getTermsPolicy().getRepaymentDayOfTheMonth());
            terms.setGraceDaysBeforeFirstRepayment(policies.getTermsPolicy().getGraceDaysBeforeFirstRepayment());
            terms.setAllowedCustomTenure(policies.getTermsPolicy().getAllowedCustomTenure());
            terms.setAllowedCustomAmount(policies.getTermsPolicy().getAllowedCustomAmount());
            terms.setAllowTopUp(policies.getTermsPolicy().getAllowTopUp());
            terms.setAllowedRestructuring(policies.getTermsPolicy().getAllowedRestructuring());
            terms.setAllowEarlyRepayment(policies.getTermsPolicy().getAllowEarlyRepayment());
            terms.setChargeEarlyRepaymentFee(policies.getTermsPolicy().getChargeEarlyRepaymentFee());
            terms.setAllowPartialDisbursement(policies.getTermsPolicy().getAllowPartialDisbursement());
            terms.setMaxDisbursementDaysAfterApproval(policies.getTermsPolicy().getMaxDisbursementDaysAfterApproval());
            terms.setMaxDisbursementTranches(policies.getTermsPolicy().getMaxDisbursementTranches());
            terms.setDisbursementMethod(policies.getTermsPolicy().getDisbursementMethod() != null ?
                    DisbursementMethod.valueOf(policies.getTermsPolicy().getDisbursementMethod()) : terms.getDisbursementMethod());
            terms.setRequireCreditBureauCheck(policies.getTermsPolicy().getRequireCreditBureauCheck());
            terms.setMaxAllowedActiveLoans(policies.getTermsPolicy().getMaxAllowedActiveLoans());
            terms.setRequireManualApproval(policies.getTermsPolicy().getRequireManualApproval());
            terms.setAllowAutoApproval(policies.getTermsPolicy().getAllowAutoApproval());
            terms.setApprovalLevelCount(policies.getTermsPolicy().getApprovalLevelCount());
            terms.setAllowBranchesOverride(policies.getTermsPolicy().getAllowBranchesOverride());
            terms.setCurrency(policies.getTermsPolicy().getCurrency());
            termsPolicy = termsPolicyRepository.save(terms);
        }

        if (policies.getInterestPolicy() != null) {
            InterestPolicy policy = interestPolicyRepository.findByProductVersion(version)
                    .orElse(new InterestPolicy());
            InterestPolicy updatedPolicy = interestPolicyMapper.toEntity(policies.getInterestPolicy(), version);
            policy.setProductVersion(updatedPolicy.getProductVersion());
            policy.setInterestType(updatedPolicy.getInterestType());
            policy.setAnnualInterestRate(updatedPolicy.getAnnualInterestRate());
            policy.setMinInterestRate(updatedPolicy.getMinInterestRate());
            policy.setMaxInterestRate(updatedPolicy.getMaxInterestRate());
            policy.setAllowedRateOverride(updatedPolicy.getAllowedRateOverride());
            policy.setAccrualMethod(updatedPolicy.getAccrualMethod());
            policy.setDayCountConversion(updatedPolicy.getDayCountConversion());
            policy.setAccrueDuringGracePeriod(updatedPolicy.getAccrueDuringGracePeriod());
            policy.setCapitalizedInterest(updatedPolicy.getCapitalizedInterest());
            interestPolicy = interestPolicyRepository.save(policy);
        }

        if (policies.getPenaltyPolicy() != null) {
            PenaltyPolicy policy = penaltyPolicyRepository.findByProductVersion(version)
                    .orElse(new PenaltyPolicy());
            PenaltyPolicy updatedPolicy = penaltyPolicyMapper.toEntity(policies.getPenaltyPolicy(), version);
            policy.setProductVersion(updatedPolicy.getProductVersion());
            policy.setGraceDaysAfterDueDate(updatedPolicy.getGraceDaysAfterDueDate());
            policy.setPenaltyType(updatedPolicy.getPenaltyType());
            policy.setCalculationMethod(updatedPolicy.getCalculationMethod());
            policy.setFixedAmount(updatedPolicy.getFixedAmount());
            policy.setPercentage(updatedPolicy.getPercentage());
            policy.setMaxPenaltyAmount(updatedPolicy.getMaxPenaltyAmount());
            policy.setFrequency(updatedPolicy.getFrequency());
            policy.setCompoundPenalty(updatedPolicy.getCompoundPenalty());
            policy.setMaxPenaltyDays(updatedPolicy.getMaxPenaltyDays());
            penaltyPolicy = penaltyPolicyRepository.save(policy);
        }

        if (policies.getDefaultPolicy() != null) {
            ProductEligibility policy = eligibilityPolicyRepository.findByProductVersion(version)
                    .orElse(new ProductEligibility());
            ProductEligibility updatedPolicy = productEligibilityMapper.toEntity(policies.getEligibilityPolicy(), version);
            policy.setProductVersion(updatedPolicy.getProductVersion());
            policy.setMinApplicantAge(updatedPolicy.getMinApplicantAge());
            policy.setMaxApplicantAge(updatedPolicy.getMaxApplicantAge());
            policy.setMinMonthlyIncome(updatedPolicy.getMinMonthlyIncome());
            policy.setMinBusinessAgeMonths(updatedPolicy.getMinBusinessAgeMonths());
            policy.setAllowedClientTypes(updatedPolicy.getAllowedClientTypes());
            policy.setMinEmploymentDurationMonths(updatedPolicy.getMinEmploymentDurationMonths());
            policy.setAllowedRegions(updatedPolicy.getAllowedRegions());
            policy.setAllowExistingBorrowers(updatedPolicy.getAllowExistingBorrowers());
            policy.setAllowFirstTimeBorrowers(updatedPolicy.getAllowFirstTimeBorrowers());
            eligibilityPolicy = eligibilityPolicyRepository.save(policy);
        }

        PoliciesResponse response = new PoliciesResponse();
        response.setDefaultPolicy(defaultPolicy != null ? defaultPolicyMapper.toResponse(defaultPolicy) : null);
        response.setTermsPolicy(termsPolicy != null ? termsPolicyMapper.toResponse(termsPolicy) : null);
        response.setInterestPolicy(interestPolicy != null ? interestPolicyMapper.toResponse(interestPolicy) : null);
        response.setPenaltyPolicy(penaltyPolicy != null ? penaltyPolicyMapper.toResponse(penaltyPolicy) : null);
        response.setEligibilityPolicy(eligibilityPolicy != null ? productEligibilityMapper.toResponse(eligibilityPolicy) : null);
        log.debug("All policies updated for version {}", versionId);
        return response;
    }

    @Override
    public List<ProductChargeResponse> updateCharges(Long versionId, List<ProductChargeRequest> chargeDTOs) {
        log.info("Updating {} charges for version: {}", chargeDTOs.size(), versionId);

        LoanProductVersion version = validateVersionForConfigurationUpdate(versionId);

        chargeRepository.deleteByProductVersion(version);

        List<ProductCharge> charges = chargeDTOs.stream()
                .map(dto -> {
                    ProductCharge charge = chargeMapper.toEntity(dto,version);
                    charge.setProductVersion(version);
                    return charge;
                })
                .collect(Collectors.toList());

        List<ProductCharge> savedCharges = chargeRepository.saveAll(charges);
        log.debug("Saved {} charges for version {}", savedCharges.size(), versionId);

        return savedCharges.stream()
                .map(chargeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RequirementsResponse updateRequirements(Long versionId, RequirementsRequests requirements) {
        log.info("Updating requirements for version: {}", versionId);

        LoanProductVersion version = validateVersionForConfigurationUpdate(versionId);

        RequirementsResponse response = new RequirementsResponse();

        if (requirements.getAgricultureRequirement() != null) {
            var existing = agricultureRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.AgricultureRequirement());
            var updated = agricultureRequirementMapper.toEntity(requirements.getAgricultureRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setFarmDetailsRequired(updated.getFarmDetailsRequired());
            existing.setProductionCycleRequired(updated.getProductionCycleRequired());
            existing.setOffTakerAgreement(updated.getOffTakerAgreement());
            existing.setFarmInspectionRequired(updated.getFarmInspectionRequired());
            existing.setMinFarmSize(updated.getMinFarmSize());

            var saved = agricultureRequirementRepository.save(existing);
            response.setAgricultureRequirement(agricultureRequirementMapper.toResponse(saved));
        }

        if (requirements.getSolidarityRequirement() != null) {
            var existing = solidarityRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.SolidarityRequirement());
            var updated = solidarityRequirementMapper.toEntity(requirements.getSolidarityRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setGroupRegistrationRequired(updated.getGroupRegistrationRequired());
            existing.setGroupGuaranteeRequired(updated.getGroupGuaranteeRequired());
            existing.setGroupMeetingRecordsRequired(updated.getGroupMeetingRecordsRequired());
            existing.setMinGroupMembers(updated.getMinGroupMembers());
            existing.setMaxGroupMembers(updated.getMaxGroupMembers());

            var saved = solidarityRequirementRepository.save(existing);
            response.setSolidarityRequirement(solidarityRequirementMapper.toResponse(saved));
        }

        if (requirements.getAssetRequirement() != null) {
            var existing = assetRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.AssetRequirement());
            var updated = assetRequirementMapper.toEntity(requirements.getAssetRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setQuotationRequired(updated.getQuotationRequired());
            existing.setSupplierVerificationRequired(updated.getSupplierVerificationRequired());
            existing.setAssetInsuranceRequired(updated.getAssetInsuranceRequired());
            existing.setAssetOwnershipTransferredToLender(updated.getAssetOwnershipTransferredToLender());

            var saved = assetRequirementRepository.save(existing);
            response.setAssetRequirement(assetRequirementMapper.toResponse(saved));
        }

        if (requirements.getCollateralRequirement() != null) {
            var existing = collateralRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.CollateralRequirement());
            var updated = collateralRequirementMapper.toEntity(requirements.getCollateralRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setAllowedTypes(updated.getAllowedTypes());
            existing.setMinCount(updated.getMinCount());
            existing.setMaxCount(updated.getMaxCount());
            existing.setDescriptionRequired(updated.getDescriptionRequired());
            existing.setOwnershipProofRequired(updated.getOwnershipProofRequired());
            existing.setInsuranceRequired(updated.getInsuranceRequired());
            existing.setValuationRequired(updated.getValuationRequired());
            existing.setPhotoRequired(updated.getPhotoRequired());
            existing.setMinAverageMonthlyTurnOver(updated.getMinAverageMonthlyTurnOver());

            var saved = collateralRequirementRepository.save(existing);
            response.setCollateralRequirement(collateralRequirementMapper.toResponse(saved));
        }

        if (requirements.getGuaranteeRequirement() != null) {
            var existing = guarantorRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.GuarantorRequirement());
            var updated = guarantorRequirementMapper.toEntity(requirements.getGuaranteeRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setMinGuarantors(updated.getMinGuarantors());
            existing.setMaxGuarantors(updated.getMaxGuarantors());
            existing.setGuarantorIncomeProofRequired(updated.getGuarantorIncomeProofRequired());
            existing.setGuarantorEmploymentRequired(updated.getGuarantorEmploymentRequired());
            existing.setGuarantorRelationRequired(updated.getGuarantorRelationRequired());
            existing.setMinGuarantorIncome(updated.getMinGuarantorIncome());

            var saved = guarantorRequirementRepository.save(existing);
            response.setGuaranteeRequirement(guarantorRequirementMapper.toResponse(saved));
        }

        if (requirements.getEmploymentRequirement() != null) {
            var existing = employmentRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.EmploymentRequirement());
            var updated = employmentRequirementMapper.toEntity(requirements.getEmploymentRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setJobContractRequired(updated.getJobContractRequired());
            existing.setPaySlipRequired(updated.getPaySlipRequired());
            existing.setPayrollDeductionRequired(updated.getPayrollDeductionRequired());
            existing.setBankStatementRequired(updated.getBankStatementRequired());
            existing.setMinMonthsEmployed(updated.getMinMonthsEmployed());
            existing.setMinNetSalary(updated.getMinNetSalary());

            var saved = employmentRequirementRepository.save(existing);
            response.setEmploymentRequirement(employmentRequirementMapper.toResponse(saved));
        }

        if (requirements.getBusinessRequirement() != null) {
            var existing = businessRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.BusinessRequirement());
            var updated = businessRequirementMapper.toEntity(requirements.getBusinessRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setRegistrationRequired(updated.getRegistrationRequired());
            existing.setBusinessLicenseRequired(updated.getBusinessLicenseRequired());
            existing.setCashFlowStatementRequired(updated.getCashFlowStatementRequired());
            existing.setBankStatementRequired(updated.getBankStatementRequired());
            existing.setTinCertificateRequired(updated.getTinCertificateRequired());
            existing.setTinNumberRequired(updated.getTinNumberRequired());
            existing.setInsuranceComprehensiveCoverRequired(updated.getInsuranceComprehensiveCoverRequired());
            existing.setMinYearsInOperation(updated.getMinYearsInOperation());
            existing.setMinAverageMonthlyTurnOver(updated.getMinAverageMonthlyTurnOver());

            var saved = businessRequirementRepository.save(existing);
            response.setBusinessRequirement(businessRequirementMapper.toResponse(saved));
        }

        if (requirements.getDigitalConsentRequirement() != null) {
            var existing = digitalConsentRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.DigitalConsentRequirement());
            var updated = digitalConsentRequirementMapper.toEntity(requirements.getDigitalConsentRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setMobileNumberOwnershipRequired(updated.getMobileNumberOwnershipRequired());
            existing.setDigitalCreditScore(updated.getDigitalCreditScore());
            existing.setDataConsentRequired(updated.getDataConsentRequired());

            var saved = digitalConsentRequirementRepository.save(existing);
            response.setDigitalConsentRequirement(digitalConsentRequirementMapper.toResponse(saved));
        }

        if (requirements.getEducationRequirement() != null) {
            var existing = educationRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.EducationRequirement());
            var updated = educationRequirementMapper.toEntity(requirements.getEducationRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setAdmissionLetterRequired(updated.getAdmissionLetterRequired());
            existing.setFeeStructureRequired(updated.getFeeStructureRequired());
            existing.setSponsorRequired(updated.getSponsorRequired());
            existing.setGuarantorRequired(updated.getGuarantorRequired());
            existing.setRelatedEducationCertificateRequired(updated.getRelatedEducationCertificateRequired());

            var saved = educationRequirementRepository.save(existing);
            response.setEducationRequirement(educationRequirementMapper.toResponse(saved));
        }

        if (requirements.getFinancialHistoryRequirement() != null) {
            var existing = financialHistoryRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.FinancialHistoryRequirement());
            var updated = financialHistoryRequirementMapper.toEntity(requirements.getFinancialHistoryRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setBankStatementRequired(updated.getBankStatementRequired());
            existing.setCreditHistoryCheckRequired(updated.getCreditHistoryCheckRequired());
            existing.setMobileMoneyStatementRequired(updated.getMobileMoneyStatementRequired());
            existing.setBankStatementMonths(updated.getBankStatementMonths());
            existing.setMobileMoneyStatementMonths(updated.getMobileMoneyStatementMonths());
            existing.setMinCreditScore(updated.getMinCreditScore());

            var saved = financialHistoryRequirementRepository.save(existing);
            response.setFinancialHistoryRequirement(financialHistoryRequirementMapper.toResponse(saved));
        }

        if (requirements.getHousingRequirement() != null) {
            var existing = housingRequirementRepository.findByProductVersion(version)
                    .stream().findFirst().orElse(new com.machidior.configuration_service.product.requirement.HousingRequirement());
            var updated = housingRequirementMapper.toEntity(requirements.getHousingRequirement(), version);

            existing.setProductVersion(updated.getProductVersion());
            existing.setType(updated.getType());
            existing.setEnabled(updated.getEnabled());
            existing.setMandatory(updated.getMandatory());
            existing.setOwnershipProofRequired(updated.getOwnershipProofRequired());
            existing.setBuildingPlanRequired(updated.getBuildingPlanRequired());
            existing.setSiteInspectionRequired(updated.getSiteInspectionRequired());
            existing.setValuationRequired(updated.getValuationRequired());
            existing.setMaxLoanToValueRatio(updated.getMaxLoanToValueRatio());

            var saved = housingRequirementRepository.save(existing);
            response.setHousingRequirement(housingRequirementMapper.toResponse(saved));
        }

        log.debug("All requirements updated for version {}", versionId);
        return response;
    }

    @Override
    public ConfigurationsResponse getFullConfiguration(Long versionId) {
        log.debug("Getting full configuration for version: {}", versionId);

        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        ConfigurationsResponse config = new ConfigurationsResponse();

        config.setPolicies(getPolicies(versionId));

        List<ProductCharge> charges = chargeRepository.findByProductVersion(version);
        config.setCharges(charges.stream()
                .map(chargeMapper::toResponse)
                .collect(Collectors.toList()));

        config.setRequirements(getRequirements(versionId));

        return config;
    }

    private LoanProductVersion validateVersionForConfigurationUpdate(Long versionId) {
        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        if (version.getStatus() != VersionStatus.DRAFT) {
            throw new ImmutableVersionException(
                    String.format("Cannot update configurations for %s version. Create a new DRAFT version first.",
                            version.getStatus()));
        }

        return version;
    }

    private PoliciesResponse getPolicies(Long versionId) {
        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        PoliciesResponse policies = new PoliciesResponse();

        defaultPolicyRepository.findByProductVersion(version)
                .ifPresent(policy -> policies.setDefaultPolicy(defaultPolicyMapper.toResponse(policy)));

        termsPolicyRepository.findByProductVersion(version)
                .ifPresent(policy -> policies.setTermsPolicy(termsPolicyMapper.toResponse(policy)));

        interestPolicyRepository.findByProductVersion(version)
                .ifPresent(policy -> policies.setInterestPolicy(interestPolicyMapper.toResponse(policy)));

        penaltyPolicyRepository.findByProductVersion(version)
                .ifPresent(policy -> policies.setPenaltyPolicy(penaltyPolicyMapper.toResponse(policy)));

        eligibilityPolicyRepository.findByProductVersion(version)
                .ifPresent(policy -> policies.setEligibilityPolicy(productEligibilityMapper.toResponse(policy)));

        return policies;
    }

    private RequirementsResponse getRequirements(Long versionId) {
        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        RequirementsResponse requirements = new RequirementsResponse();

        agricultureRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setAgricultureRequirement(agricultureRequirementMapper.toResponse(r)));

        assetRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setAssetRequirement(assetRequirementMapper.toResponse(r)));

        collateralRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setCollateralRequirement(collateralRequirementMapper.toResponse(r)));

        guarantorRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setGuaranteeRequirement(guarantorRequirementMapper.toResponse(r)));

        employmentRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setEmploymentRequirement(employmentRequirementMapper.toResponse(r)));

        businessRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setBusinessRequirement(businessRequirementMapper.toResponse(r)));

        digitalConsentRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setDigitalConsentRequirement(digitalConsentRequirementMapper.toResponse(r)));

        educationRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setEducationRequirement(educationRequirementMapper.toResponse(r)));

        financialHistoryRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setFinancialHistoryRequirement(financialHistoryRequirementMapper.toResponse(r)));

        housingRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setHousingRequirement(housingRequirementMapper.toResponse(r)));

        solidarityRequirementRepository.findByProductVersion(version)
                .stream()
                .findFirst()
                .ifPresent(r -> requirements.setSolidarityRequirement(solidarityRequirementMapper.toResponse(r)));

        return requirements;
    }
}
