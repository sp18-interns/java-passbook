package com.passbook.sparkeighteen.peristence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile", schema = "public")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Pattern(regexp = "(^$|[0-9]{10})")
    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonIgnore
    @NotNull
    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "pan", length = 10)
    private String pan;

    @NotNull
    @Size(min = 12, max = 12)
    @Column(name = "aadhar", length = 12)
    private String aadhar;

}
