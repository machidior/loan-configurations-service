package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;

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
    private Long productId;
    private Boolean requiresGuarantor;
    private Integer minGuarantors;
    private Boolean requiresCollateral;
    @Enumerated(EnumType.STRING)
    private List<CollateralType> allowedCollateralTypes;
}
