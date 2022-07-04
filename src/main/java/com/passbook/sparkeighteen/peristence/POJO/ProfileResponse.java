package com.passbook.sparkeighteen.peristence.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Profile response is for when user update their profile then get back response, in response we get this values and message.
 */
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
    private String address;
}
