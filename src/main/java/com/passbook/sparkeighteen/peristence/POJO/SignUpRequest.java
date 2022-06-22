package com.passbook.sparkeighteen.peristence.POJO;

import com.passbook.sparkeighteen.peristence.POJO.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "First name cannot be left Blank")
    private String firstname;

    private String lastname;

    @NotBlank(message = "Gender cannot be left Blank")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "Date of Birth of a user cannot be blank")
    private LocalDate dob;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    private String email;
}
