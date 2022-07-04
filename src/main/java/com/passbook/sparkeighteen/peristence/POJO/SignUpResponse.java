package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Sign up response is for when user create their profile then get back to this data as response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {

    private Integer userID;
    private String message;

}
