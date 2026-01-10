package com.machidior.configuration_service.product.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.LoanProductVersion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialHistoryRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    private Boolean bankStatementRequired;
    private Boolean creditHistoryCheckRequired;
    private Boolean mobileMoneyStatementRequired;

    private Integer bankStatementMonths;
    private Integer mobileMoneyStatementMonths;
    private Integer minCreditScore;
}
