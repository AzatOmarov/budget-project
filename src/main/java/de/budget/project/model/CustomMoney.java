package de.budget.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CustomMoney {

    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "AMOUNT")
    private BigDecimal amount;


}
