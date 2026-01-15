package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.request.ApproveVersionRequest;
import com.machidior.configuration_service.dtos.request.LoanProductVersionRequest;
import com.machidior.configuration_service.dtos.response.ActiveProductVersionBasicDetails;
import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface LoanProductVersionService {
    void createInitialVersion(Long productId, String createdBy);

    LoanProductVersionResponse updateVersion(LoanProductVersionRequest request, Long versionId);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    LoanProductVersionResponse cloneVersion(Long sourceVersionId, String createdBy);

    LoanProductVersionResponse approveVersion(Long versionId, ApproveVersionRequest request);

    LoanProductVersionResponse activateVersion(Long versionId);

    LoanProductVersionResponse archiveVersion(Long versionId, String archivedBy);

    LoanProductVersionResponse getDraftVersion(Long productId);

    LoanProductVersionResponse getActiveVersion(Long productId);

    LoanProductVersionResponse getVersionById(Long versionId);

    ActiveProductVersionBasicDetails getActiveVersionBasicDetails(Long productId);
}
