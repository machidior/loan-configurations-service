package com.machidior.configuration_service.product.policy;

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
public class DefaultPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanProductVersion productVersion;

    private Integer daysOverdueToDefault;

    private Boolean freezeInterestAccrual = true;

    private Boolean freezePenaltyAccrual = false;

    private Boolean blockFurtherDisbursement = true;

    private Boolean markAsNonPerforming = true;

    private Integer maxAllowedOverdueDays;

    private Boolean blockIfAnyDefaultedLoan;

}
