package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = "amount mandatory to transaction ")
    private Float amount;

    private String name;

    private String note;

    @NotNull
    private TransactionType transactionType;
}
