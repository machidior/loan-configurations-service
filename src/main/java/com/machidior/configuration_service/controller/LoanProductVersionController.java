package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.request.ApproveVersionRequest;
import com.machidior.configuration_service.dtos.request.LoanProductVersionRequest;
import com.machidior.configuration_service.dtos.response.ActiveProductVersionBasicDetails;
import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;
import com.machidior.configuration_service.service.LoanProductVersionService;
import com.machidior.configuration_service.service.ProductVersionCloneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan-product-versions")
@RequiredArgsConstructor
public class LoanProductVersionController {
    private final LoanProductVersionService productVersionService;
    private final ProductVersionCloneService productVersionCloneService;

    @PutMapping("/{versionId}")
    public ResponseEntity<LoanProductVersionResponse> updateVersion(@RequestBody LoanProductVersionRequest request,
                                                                    @PathVariable Long versionId) {
        LoanProductVersionResponse response = productVersionService.updateVersion(request, versionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{versionId}/clone")
    public ResponseEntity<LoanProductVersionResponse> cloneVersion(@PathVariable Long versionId) {
        String createdBy = "system"; // BUG: retrieve this from the authenticated user context
        LoanProductVersionResponse response = productVersionService.cloneVersion(versionId, createdBy);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{versionId}/approve")
    public ResponseEntity<LoanProductVersionResponse> approveVersion(@PathVariable Long versionId, @RequestBody ApproveVersionRequest request) {
        request.setApprovedBy("system"); // BUG: retrieve this from the authenticated user context
        LoanProductVersionResponse response = productVersionService.approveVersion(versionId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{versionId}/archive")
    public ResponseEntity<LoanProductVersionResponse> archiveVersion(@PathVariable Long versionId) {
        String archivedBy = "system"; // BUG: retrieve this from the authenticated user context
        LoanProductVersionResponse response = productVersionService.archiveVersion(versionId, archivedBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/draft/{productId}")
    public ResponseEntity<LoanProductVersionResponse> getDraftVersion(@PathVariable Long productId) {
        LoanProductVersionResponse response = productVersionService.getDraftVersion(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active/{productId}")
    public ResponseEntity<LoanProductVersionResponse> getActiveVersion(@PathVariable Long productId) {
        LoanProductVersionResponse response = productVersionService.getActiveVersion(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{versionId}")
    public ResponseEntity<LoanProductVersionResponse> getVersionById(@PathVariable Long versionId) {
        LoanProductVersionResponse response = productVersionService.getVersionById(versionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/activate/{versionId}")
    public ResponseEntity<LoanProductVersionResponse> activateVersion(@PathVariable Long versionId) {
        LoanProductVersionResponse response = productVersionService.activateVersion(versionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active/{productId}/basic-details")
    public ResponseEntity<ActiveProductVersionBasicDetails> getActiveVersionBasicDetails(@PathVariable Long productId) {
        ActiveProductVersionBasicDetails response = productVersionService.getActiveVersionBasicDetails(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/clone/{versionId}")
    public ResponseEntity<LoanProductVersionResponse> cloneProductVersion(@PathVariable Long versionId) {
        LoanProductVersionResponse response = productVersionCloneService.cloneVersion(versionId);
        return ResponseEntity.ok(response);
    }
}
