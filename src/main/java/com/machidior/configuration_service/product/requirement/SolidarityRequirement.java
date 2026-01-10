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
public class SolidarityRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    private Boolean groupRegistrationRequired;
    private Boolean groupGuaranteeRequired;
    private Boolean groupMeetingRecordsRequired;

    private Integer minGroupMembers;
    private Integer maxGroupMembers;
}
