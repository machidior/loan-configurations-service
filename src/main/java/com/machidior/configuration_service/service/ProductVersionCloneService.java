package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;

public interface ProductVersionCloneService {
    LoanProductVersionResponse cloneVersion(Long sourceVersionId);
}

