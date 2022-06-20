package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotBlank
    @NotEmpty(message = "name is mandatory")
    private String name;

    @NotBlank
    @NotEmpty(message = "mobile number is mandatory")
    private String mobileNumber;

    @NotEmpty(message = "email is mandatory")
    private String email;

    @NotEmpty(message = "age is mandatory")
    private Integer age;

    @NotEmpty(message = "gender is mandatory")
    private String gender;

    //    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "address is mandatory")
    private String address;

    @NotBlank
    @NotEmpty(message = "pan number is mandatory")
    private String panNumber;

    @NotBlank
    @NotEmpty(message = "aadhar number is mandatory")
    private BigInteger aadharNumber;


}
