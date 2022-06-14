package com.passbook.sparkeighteen.peristence.POJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "email is mandatory")
    private String email;

    @NotEmpty(message = "password is mandatory")
    private String password;

}
