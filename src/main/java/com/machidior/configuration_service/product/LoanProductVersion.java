package com.machidior.configuration_service.product;

import com.machidior.configuration_service.enums.LoanProductCategory;
import com.machidior.configuration_service.enums.VersionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_product_versions",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "version"})}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private LoanProduct product;

    // version number per product
    @Column(name = "version", nullable = false)
    private Integer version;

    // whether this version is locked (immutable)
    private Boolean locked = false;

    private Boolean active = false;

    @Enumerated(EnumType.STRING)
    private VersionStatus status;
    private String name;
    private String code;
    private String description;

    @Enumerated(EnumType.STRING)
    private LoanProductCategory category;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;
    private String approvedBy;
    private LocalDateTime approvedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String createdBy;

}
