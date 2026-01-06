package com.machidior.configuration_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

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
