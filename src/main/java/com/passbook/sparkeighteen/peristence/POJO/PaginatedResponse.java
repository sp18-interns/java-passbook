package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @param <T>  Entity PaginatedResponse used to get response with these fields.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponse<T> {

    private List<?> content;
    private Integer totalPages;
    private Integer currentPage;
    private Integer pageSize;
    private Long totalElements;
    private String message;

}
