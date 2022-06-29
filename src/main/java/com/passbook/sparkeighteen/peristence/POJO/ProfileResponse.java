package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private Integer userID;
    private String firstname;
    private String lastname;
    private String message;
    private Integer age;
    private String mobileNumber;
    private String pan;
    private String aadhar;
    private Gender gender;
}
