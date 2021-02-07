package com.acevedo.playground.companiesloader.dto;

import com.acevedo.playground.companiesloader.model.CompanyEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CompanyDto {
    private String name;

    private String country;

    private String moneyRaised;

    public static CompanyDto of(CompanyEntity entity){
        return builder()
                .name(entity.getName())
                .moneyRaised(entity.getTotalMoneyRaised())
                .build();
    }
}
