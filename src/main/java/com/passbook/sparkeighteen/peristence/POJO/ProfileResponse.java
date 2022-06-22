package com.passbook.sparkeighteen.peristence.POJO;

import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private ProfileEntity profile;
    private String message;
}
