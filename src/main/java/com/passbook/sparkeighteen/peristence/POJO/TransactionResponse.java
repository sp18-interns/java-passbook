package com.passbook.sparkeighteen.peristence.POJO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Transaction response where in response is generated on performing a transaction.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    /**
     * To get the transaction details of the particular transaction from transactionId
     */
    private Integer txnID;

    /**
     * The amount which have been transacted in the form of credit or debit
     */
    private Float amount;

    /**
     * Displayed note or description of the current processed transaction.
     */
    private String note;

    /**
     * Display the transaction type of the transaction completed like credit or debit
     */
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    /**
     * Displays the status of the transaction made
     */
    @NotBlank(message = "Transaction message cannot be blank")
    private String message;

    /**
     * shows the latest closing balance of the user
     */
    private Float closingBalance;

    /**
     * Displays the exact time and date of the transaction completed.
     */
    @JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
    @NotNull
    private LocalDateTime time;

}