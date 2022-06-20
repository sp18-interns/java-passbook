package com.passbook.sparkeighteen.peristence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    // CREATE TABLE IF NOT EXISTS USER_PROFILE(id UUID, user_id UUID,name varchar(50), mobile varchar(10),age int4,gender varchar(50), email varchar(50), password varchar(50))
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID profileId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @Size(max = 10)
    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
//    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "CRN")
    private Integer CRN;

    @Column(name = "pan_number")
    private String panNumber;

    @Size(max = 12)
    @Column(name = "aadhar_number")
    private BigInteger aadharNumber;

}
