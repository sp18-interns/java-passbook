package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionFilter class will use to filter the transactions based on the amount, note, timeInterval fields.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionFilter {

    private float amount;
    private String note;
    private TimeInterval timeInterval;

}
