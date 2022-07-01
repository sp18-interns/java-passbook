package com.passbook.sparkeighteen.peristence.POJO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Float amount;

    private String note;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String message;

    private Float closingBalance;

    @JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss a", timezone = "Asia/Kolkata")
    @NotNull
    private LocalDateTime time;

}

