package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Login response is for when user login successfully then get back a response where get message and others details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

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
