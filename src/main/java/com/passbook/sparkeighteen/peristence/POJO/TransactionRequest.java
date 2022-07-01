package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Transaction amount cannot be null")
    @NegativeOrZero(message = "Transaction amount cannot be zero or negative")
    private Float amount;

    private String name;

    private String note;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}