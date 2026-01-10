package com.machidior.configuration_service.product.policy;

import com.machidior.configuration_service.enums.DayCountConversion;
import com.machidior.configuration_service.enums.InterestAccrualMethod;
import com.machidior.configuration_service.enums.InterestType;
import com.machidior.configuration_service.product.LoanProductVersion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestType interestType;

    @Column(nullable = false)
    private BigDecimal annualInterestRate;

    private BigDecimal minInterestRate;
    private BigDecimal maxInterestRate;

    private Boolean allowedRateOverride = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestAccrualMethod accrualMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayCountConversion dayCountConversion;

    private Boolean accrueDuringGracePeriod = false;

    private Boolean capitalizedInterest = false;

}
