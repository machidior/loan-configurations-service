package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
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
     private LoanProductType productType;
     private String name;
     private Boolean active;
}
