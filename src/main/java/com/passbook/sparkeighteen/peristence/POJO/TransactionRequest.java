package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

/**
 * Transaction request where in request is initiated to perform transaction
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    /**
     * amount to be transacted through debit/credit which cannot be null/zero/negative
     */
    @NotNull(message = "Transaction amount cannot be null")
    @NegativeOrZero(message = "Transaction amount cannot be zero or negative")
    private Float amount;

    /**
     * name of the person whom you want to transfer or receive amount from
     */
    private String name;

    /**
     * note or a description of the transaction you made for example to buy a cold drink.
     */
    private String note;

    /**
     * type of transaction to be executed - Credit or Debit
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}