package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotBlank(message = "mobile number is mandatory")
    private String mobileNumber;

    @NotBlank(message = "address is mandatory")
    private String address;

    //    @NotBlank(message = "pan number is mandatory")
    private String pan;

    @NotBlank(message = "aadhar number is mandatory")
    private String aadhar;
}