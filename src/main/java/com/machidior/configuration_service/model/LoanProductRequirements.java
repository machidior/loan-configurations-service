package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private LoanProduct loanProduct;
    private Boolean requiresGuarantor;
    private Integer minGuarantors;
    private Boolean requiresCollateral;
    @Enumerated(EnumType.STRING)
    private List<CollateralType> allowedCollateralTypes;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
