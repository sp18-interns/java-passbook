package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * time interval class used by transaction filter, which will use to filter transaction based on time interval.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeInterval {
    private LocalDateTime from;
    private LocalDateTime to;

}
