package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.request.ApproveVersionRequest;
import com.machidior.configuration_service.dtos.request.LoanProductVersionRequest;
import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;


public interface LoanProductVersionService {
    void createInitialVersion(Long productId, String createdBy);

    LoanProductVersionResponse updateVersion(LoanProductVersionRequest request, Long versionId);

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    LoanProductVersionResponse cloneVersion(Long sourceVersionId, String createdBy);

    LoanProductVersionResponse approveVersion(Long versionId, ApproveVersionRequest request);

    LoanProductVersionResponse archiveVersion(Long versionId, String archivedBy);

    LoanProductVersionResponse getDraftVersion(Long productId);

    LoanProductVersionResponse getActiveVersion(Long productId);

    LoanProductVersionResponse getVersionById(Long versionId);

}
