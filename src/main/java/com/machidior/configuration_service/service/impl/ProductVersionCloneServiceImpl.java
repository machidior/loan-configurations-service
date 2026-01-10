package com.machidior.configuration_service.service.impl;

import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;
import com.machidior.configuration_service.mapper.LoanProductVersionMapper;
import com.machidior.configuration_service.product.clone.ProductConfigCloneRegistry;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.LoanProductVersionRepository;
import com.machidior.configuration_service.service.ProductVersionCloneService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductVersionCloneServiceImpl implements ProductVersionCloneService {

    private final LoanProductVersionRepository versionRepository;
    private final LoanProductVersionMapper versionMapper;
    private final ProductConfigCloneRegistry cloneRegistry;

    public ProductVersionCloneServiceImpl(
            LoanProductVersionRepository versionRepository,
            LoanProductVersionMapper versionMapper,
            ProductConfigCloneRegistry cloneRegistry
    ) {
        this.versionRepository = versionRepository;
        this.versionMapper = versionMapper;
        this.cloneRegistry = cloneRegistry;
    }

    @Override
    @Transactional
    public LoanProductVersionResponse cloneVersion(Long sourceVersionId) {
        LoanProductVersion source = versionRepository.findById(sourceVersionId)
                .orElseThrow(() -> new IllegalArgumentException("Source version not found: " + sourceVersionId));

        Optional<LoanProductVersion> latest = versionRepository.findTopByProductOrderByVersionDesc(source.getProduct());
        int nextVersion = latest.map(v -> v.getVersion() + 1).orElse(1);

        LoanProductVersion target = LoanProductVersion.builder()
                .product(source.getProduct())
                .version(nextVersion)
                .locked(false)
                .active(false)
                .name(source.getName())
                .code(source.getCode())
                .description(source.getDescription())
                .category(source.getCategory())
                .effectiveFrom(source.getEffectiveFrom())
                .build();

        versionRepository.save(target);

        cloneRegistry.cloneAll(source, target);

        source.setActive(false);
        versionRepository.save(source);

        return versionMapper.toResponse(target);
    }
}
