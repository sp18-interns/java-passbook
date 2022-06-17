package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.ProfileRequest;
import com.passbook.sparkeighteen.peristence.POJO.ProfileResponse;
import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
import com.passbook.sparkeighteen.peristence.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        ProfileEntity save = profileRepository.save(ProfileEntity.builder()
                .name(profileRequest.getName())
                .mobile_number(profileRequest.getMobile_number())
                .email(profileRequest.getEmail())
                .age(profileRequest.getAge())
                .gender(profileRequest.getGender())
                .date_of_birth(profileRequest.getDate_of_birth())
                .address(profileRequest.getAddress())
                .pan_number(profileRequest.getPan_number())
                .aadhar_number(profileRequest.getAadhar_number())
                .build());

        return ProfileResponse.builder()
                .message("Profile Created Successfully.")
                .build();
    }
}
