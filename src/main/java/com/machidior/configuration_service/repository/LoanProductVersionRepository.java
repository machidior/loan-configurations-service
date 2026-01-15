package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.enums.VersionStatus;
import com.machidior.configuration_service.product.LoanProduct;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanProductVersionRepository extends JpaRepository<LoanProductVersion, Long> {
    Optional<LoanProductVersion> findTopByProductOrderByVersionDesc(LoanProduct product);

    Optional<LoanProductVersion> findByProductAndActiveTrue(LoanProduct product);

    List<LoanProductVersion> findByProductOrderByVersionDesc(LoanProduct product);

    boolean existsByProduct(LoanProduct product);

    Optional<LoanProductVersion> findByProductAndStatus(LoanProduct product, VersionStatus versionStatus);

    @Query("SELECT COALESCE(MAX(v.version), 0) FROM LoanProductVersion v WHERE v.product.id = :productId")
    Optional<Integer> findMaxVersionByProduct(@Param("productId") Long productId);

//    ToDo: Check for overlapping active versions
//    @Query("""
//    SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END
//    FROM LoanProductVersion v
//    WHERE v.product.id = :productId
//      AND v.status = com.machidior.configuration_service.enums.VersionStatus.ACTIVE
//      AND (
//            (:effectiveTo IS NULL AND v.effectiveTo IS NULL)
//            OR (
//                :effectiveFrom <= v.effectiveTo OR v.effectiveTo IS NULL
//            )
//            AND (
//                :effectiveTo >= v.effectiveFrom OR :effectiveTo IS NULL
//            )
//      )
//      AND (
//            :excludeVersionId IS NULL OR v.id <> :excludeVersionId
//      )
//""")
//    boolean hasOverlappingActiveVersion(
//            @Param("productId") Long productId,
//            @Param("effectiveFrom") LocalDate effectiveFrom,
//            @Param("effectiveTo") LocalDate effectiveTo,
//            @Param("excludeVersionId") Long excludeVersionId
//    );
//
//
//    default boolean hasOverlappingActiveVersion(
//            Long productId,
//            LocalDate effectiveFrom,
//            LocalDate effectiveTo
//    ) {
//        return hasOverlappingActiveVersion(productId, effectiveFrom, effectiveTo, null);
//    }


//    ToDo: Find active versions at a specific date
//    @Query("""
//        SELECT v FROM LoanProductVersion v
//        WHERE v.product.id = :productId
//        AND v.status = com.machidior.configuration_service.enums.VersionStatus.ACTIVE
//        AND v.effectiveFrom <= :date
//        AND (v.effectiveTo IS NULL OR v.effectiveTo >= :date)
//    """)
//    List<LoanProductVersion> findActiveVersionsAtDate(
//            @Param("productId") Long productId,
//            @Param("date") LocalDate date);

    Optional<LoanProductVersion> findByProductAndVersion(LoanProduct product, Integer versionNumber);

    Long countByProductAndStatus(LoanProduct product, VersionStatus status);

    List<LoanProductVersion> findByStatus(VersionStatus status);

    List<LoanProductVersion> findAllByProductAndStatus(LoanProduct product, VersionStatus versionStatus);

//    ToDo: Find versions expiring between dates
//    @Query("""
//        SELECT v FROM LoanProductVersion v
//        WHERE v.status = com.machidior.configuration_service.enums.VersionStatus.ACTIVE
//        AND v.effectiveTo IS NOT NULL
//        AND v.effectiveTo BETWEEN :startDate AND :endDate
//    """)
//    List<LoanProductVersion> findVersionsExpiringBetween(
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate);
}
