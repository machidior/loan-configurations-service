package com.machidior.configuration_service.service.impl;

import com.machidior.configuration_service.dtos.request.ApproveVersionRequest;
import com.machidior.configuration_service.dtos.request.LoanProductVersionRequest;
import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;
import com.machidior.configuration_service.enums.VersionStatus;
import com.machidior.configuration_service.exceptions.BusinessException;
import com.machidior.configuration_service.exceptions.CloningException;
import com.machidior.configuration_service.exceptions.ImmutableVersionException;
import com.machidior.configuration_service.exceptions.ResourceNotFoundException;
import com.machidior.configuration_service.mapper.LoanProductVersionMapper;
import com.machidior.configuration_service.product.LoanProduct;
import com.machidior.configuration_service.product.charge.ProductCharge;
import com.machidior.configuration_service.product.clone.ProductConfigCloneRegistry;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.LoanProductVersionRepository;
import com.machidior.configuration_service.repository.*;
import com.machidior.configuration_service.service.LoanProductVersionService;
import com.machidior.configuration_service.service.ProductConfigurationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoanProductVersionServiceImpl implements LoanProductVersionService {

    private final LoanProductVersionRepository versionRepository;
    private final LoanProductRepository productRepository;
    private final ProductConfigCloneRegistry cloneRegistry;
    private final ProductChargeRepository chargeRepository;
    private final LoanProductVersionMapper versionMapper;

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

    private final ProductEligibilityRepository eligibilityPolicyRepository;
    private final InterestPolicyRepository interestPolicyRepository;
    private final PenaltyPolicyRepository penaltyPolicyRepository;
    private final DefaultPolicyRepository defaultPolicyRepository;
    private final ProductTermsRepository termsPolicyRepository;

    private final ProductConfigurationService configurationService;

    @Override
    public void createInitialVersion(Long productId, String createdBy) {
        log.info("Creating initial version for product: {}", productId);

        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        if (versionRepository.existsByProduct(product)) {
            throw new BusinessException("Product already has versions. Use cloneVersion instead.");
        }

        LoanProductVersion version = new LoanProductVersion();
        version.setProduct(product);
        version.setVersion(1);
        version.setStatus(VersionStatus.DRAFT);
        version.setCreatedAt(LocalDateTime.now());
        version.setCreatedBy(createdBy);
        version.setDescription("initial version");

        LoanProductVersion savedVersion = versionRepository.save(version);
        log.info("Created initial version {} for product {}", savedVersion.getVersion(), productId);

        versionMapper.toResponse(savedVersion);
    }

    @Override
    public LoanProductVersionResponse updateVersion(LoanProductVersionRequest request, Long versionId) {
        log.info("Updating version: {}", versionId);

        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        validateVersionForUpdate(version);

        version.setDescription(request.getDescription());

        LoanProductVersion updatedVersion = versionRepository.save(version);
        log.debug("Version {} updated", updatedVersion.getId());

        return versionMapper.toResponse(updatedVersion);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    @Override
    public LoanProductVersionResponse cloneVersion(Long sourceVersionId, String createdBy) {
        log.info("Cloning version: {}", sourceVersionId);

        LoanProductVersion sourceVersion = versionRepository.findById(sourceVersionId)
                .orElseThrow(() -> new ResourceNotFoundException("Source version not found with ID: " + sourceVersionId));

        if (sourceVersion.getStatus() != VersionStatus.ACTIVE) {
            throw new BusinessException("Can only clone ACTIVE versions. Current status: " + sourceVersion.getStatus());
        }

        LoanProduct product = sourceVersion.getProduct();

        Integer nextVersionNumber = versionRepository.findMaxVersionByProduct(product.getId())
                .orElse(0) + 1;

        LoanProductVersion newVersion = new LoanProductVersion();
        newVersion.setProduct(product);
        newVersion.setVersion(nextVersionNumber);
        newVersion.setStatus(VersionStatus.DRAFT);
        newVersion.setCreatedAt(LocalDateTime.now());
        newVersion.setCreatedBy(createdBy);
        newVersion.setDescription("Cloned from version " + sourceVersion.getVersion());

        LoanProductVersion savedNewVersion = versionRepository.save(newVersion);
        log.debug("New version {} created for cloning", savedNewVersion.getId());

        try {
            cloneRegistry.cloneAll(sourceVersion, savedNewVersion);
            log.info("Successfully cloned all configurations from version {} to version {}",
                    sourceVersion.getVersion(), savedNewVersion.getVersion());

        } catch (Exception e) {
            log.error("Failed to clone configurations. Rolling back...", e);
            throw new CloningException("Failed to clone configurations: " + e.getMessage());
        }

        return versionMapper.toResponse(savedNewVersion);
    }

    @Override
    public LoanProductVersionResponse approveVersion(Long versionId, ApproveVersionRequest request) {
        log.info("Approving version: {}", versionId);

        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        if (version.getStatus() != VersionStatus.DRAFT) {
            throw new BusinessException("Only DRAFT versions can be approved. Current status: "+ version.getStatus());
        }

        validateConfigurationCompleteness(version.getId());

        validateEffectiveDates(version, request);

        handleVersionTransition(version, request);

        version.setStatus(VersionStatus.ACTIVE);
        version.setEffectiveFrom(request.getEffectiveFrom());
        version.setEffectiveTo(request.getEffectiveTo());
        version.setApprovedAt(LocalDateTime.now());
        version.setApprovedBy(request.getApprovedBy());
        version.setDescription(request.getDescription() != null ? request.getDescription() : version.getDescription());

        LoanProductVersion approvedVersion = versionRepository.save(version);
        log.info("Version {} approved and activated", versionId);

        return versionMapper.toResponse(approvedVersion);
    }

    @Override
    public LoanProductVersionResponse archiveVersion(Long versionId, String archivedBy) {
        log.info("Archiving version: {}", versionId);

        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        if (version.getStatus() != VersionStatus.ACTIVE) {
            throw new BusinessException("Only ACTIVE versions can be archived. Current status: " + version.getStatus());
        }

        version.setStatus(VersionStatus.ARCHIVED);
        version.setEffectiveFrom(LocalDate.now()); // Archive effective immediately
        version.setDescription("Archived by " + archivedBy + " on " + LocalDateTime.now());

        LoanProductVersion archivedVersion = versionRepository.save(version);
        log.info("Version {} archived", versionId);

        return versionMapper.toResponse(archivedVersion);
    }

    @Override
    public LoanProductVersionResponse getDraftVersion(Long productId) {
        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        LoanProductVersion draftVersion = versionRepository.findByProductAndStatus(product, VersionStatus.DRAFT)
                .orElseThrow(() -> new ResourceNotFoundException("No DRAFT version found for product: " + productId));
        return versionMapper.toResponse(draftVersion);
    }

    @Override
    public LoanProductVersionResponse getActiveVersion(Long productId) {
        LoanProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        LoanProductVersion activeVersion = versionRepository.findByProductAndStatus(product, VersionStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("No ACTIVE version found for product: " + productId));
        LoanProductVersionResponse response = versionMapper.toResponse(activeVersion);
        response.setConfigurations(configurationService.getFullConfiguration(activeVersion.getId()));
        return response;
    }

    // Private validation methods
    private void validateVersionForUpdate(LoanProductVersion version) {
        if (version.getStatus() != VersionStatus.DRAFT) {
            throw new ImmutableVersionException(
                    String.format("Version %d is %s and cannot be modified. Create a new version instead.",
                            version.getVersion(), version.getStatus()));
        }
    }

    private void validateConfigurationCompleteness(Long versionId) {
        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));

        if (!eligibilityPolicyRepository.existsByProductVersion(version)) {
            throw new BusinessException("Eligibility policy is missing for version " + versionId);
        }
        if (!interestPolicyRepository.existsByProductVersion(version)) {
            throw new BusinessException("Interest policy is missing for version " + versionId);
        }
        if (!penaltyPolicyRepository.existsByProductVersion(version)) {
            throw new BusinessException("Penalty policy is missing for version " + versionId);
        }
        if (!defaultPolicyRepository.existsByProductVersion(version)) {
            throw new BusinessException("Default policy is missing for version " + versionId);
        }
        if (!termsPolicyRepository.existsByProductVersion(version)) {
            throw new BusinessException("Terms policy is missing for version " + versionId);
        }

        List<ProductCharge> mandatoryCharges = chargeRepository.findByProductVersionAndMandatory(version, true);
        if (mandatoryCharges.isEmpty()) {
            // Log warning but don't fail - some products might not have charges
            log.warn("Version {} has no mandatory charges", versionId);
        }

        if (!solidarityRequirementRepository.existsByProductVersion(version) &&
            !agricultureRequirementRepository.existsByProductVersion(version) &&
            !assetRequirementRepository.existsByProductVersion(version) &&
            !collateralRequirementRepository.existsByProductVersion(version) &&
            !guarantorRequirementRepository.existsByProductVersion(version) &&
            !employmentRequirementRepository.existsByProductVersion(version) &&
            !businessRequirementRepository.existsByProductVersion(version) &&
            !digitalConsentRequirementRepository.existsByProductVersion(version) &&
            !educationRequirementRepository.existsByProductVersion(version) &&
            !financialHistoryRequirementRepository.existsByProductVersion(version) &&
            !housingRequirementRepository.existsByProductVersion(version)) {
            log.warn("Version {} has no customer requirements defined", versionId);
        }

    }

    private void validateEffectiveDates(LoanProductVersion version, ApproveVersionRequest request) {
        if (request.getEffectiveFrom() == null) {
            throw new BusinessException("Effective from date is required for approval");
        }

        if (request.getEffectiveTo() != null && request.getEffectiveTo().isBefore(request.getEffectiveFrom())) {
            throw new BusinessException("Effective to date must be after effective from date");
        }

        // Check for overlapping active versions
        boolean hasOverlap = versionRepository.hasOverlappingActiveVersion(
                version.getProduct().getId(),
                request.getEffectiveFrom(),
                request.getEffectiveTo());

        if (hasOverlap) {
            throw new BusinessException("Effective dates overlap with existing ACTIVE version");
        }
    }

    private void handleVersionTransition(LoanProductVersion newVersion, ApproveVersionRequest request) {
        // Find currently ACTIVE version for this product
        LoanProductVersion activeVersion = versionRepository.findByProductAndStatus(
                newVersion.getProduct(), VersionStatus.ACTIVE)
                .orElseThrow(()-> new BusinessException("No ACTIVE version found for product"));

            // Check if new version replaces the active one
            if (request.getEffectiveFrom().isBefore(activeVersion.getEffectiveTo()) ||
                    (activeVersion.getEffectiveTo() == null && request.getEffectiveFrom().isAfter(activeVersion.getEffectiveFrom()))) {

                // Archive the old version
                activeVersion.setStatus(VersionStatus.ARCHIVED);
                if (request.getEffectiveFrom().isAfter(activeVersion.getEffectiveFrom())) {
                    activeVersion.setEffectiveTo(request.getEffectiveFrom().minusDays(1));
                }
                versionRepository.save(activeVersion);
                log.info("Archived overlapping version {}", activeVersion.getVersion());
            }

    }

    @Override
    public LoanProductVersionResponse getVersionById(Long versionId) {
        LoanProductVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version not found with ID: " + versionId));
        LoanProductVersionResponse response = versionMapper.toResponse(version);
        response.setConfigurations(configurationService.getFullConfiguration(version.getId()));
        return response;
    }

}