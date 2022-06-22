package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotBlank
    @NotEmpty(message = "mobile number is mandatory")
    private String mobileNumber;

    @NotEmpty(message = "address is mandatory")
    private String address;

    @NotBlank
    @NotEmpty(message = "pan number is mandatory")
    private String pan;

    @NotBlank
    @NotEmpty(message = "aadhar number is mandatory")
    private String aadhar;
}
