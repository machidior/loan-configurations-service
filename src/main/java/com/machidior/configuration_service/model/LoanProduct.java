package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.LoanProductCategory;
import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
     private LoanProductType productType;

     private String name;
     private String code;
     private String description;
     @Enumerated(EnumType.STRING)
     private LoanProductCategory category;
     private Boolean active = true;


    @CreatedDate
     private LocalDateTime createdAt;
     @LastModifiedDate
     private LocalDateTime updatedAt;
}
