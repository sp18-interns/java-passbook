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
public class KafkaTransactionRequest {

    @NotNull(message = "Transaction cannot exist without User")
    private Integer userID;

    @NotNull(message = "Transaction cannot be null")
    private TransactionRequest request;
}

