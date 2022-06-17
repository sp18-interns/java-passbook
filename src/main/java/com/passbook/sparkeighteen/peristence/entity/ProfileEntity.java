package com.passbook.sparkeighteen.peristence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profile", schema = "public")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_number")
    private String mobile_number;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
//    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate date_of_birth;

    @Column(name = "address")
    private String address;

    @Column(name = "CRN")
    private Integer CRN;

    @Column(name = "pan_number")
    private String pan_number;

    @Column(name = "aadhar_number")
    private BigInteger aadhar_number;

}
