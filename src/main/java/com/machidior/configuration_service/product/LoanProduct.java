package com.machidior.configuration_service.product;

import com.machidior.configuration_service.enums.LoanProductCategory;
import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "loan_products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "productType")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private LoanProductType productType;

    // Public external id (UUID as string) for referencing products externally
//    @Column(nullable = false, unique = true)
//    private String externalId;

    private String name;
    private String code;
    private String description;
    @Enumerated(EnumType.STRING)
    private LoanProductCategory category;
    private Boolean active = true;

    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
