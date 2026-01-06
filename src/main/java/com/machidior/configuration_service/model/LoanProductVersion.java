package com.machidior.configuration_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames =
                {"product_id", "version"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private LoanProduct product;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private Boolean locked = false;
    @Column(nullable = false)
    private Boolean active = false;

    private String description;

    private LocalDateTime effectiveFrom;

    @CreatedDate
    private LocalDateTime createdAt;
}
