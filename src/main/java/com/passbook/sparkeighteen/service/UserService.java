package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.*;
import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.ProfileRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserService(final UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public SignUpResponse signUp(@NonNull final SignUpRequest signUpRequest) throws Exception {

        final Optional<UserEntity> user =
                userRepository.findByEmail(signUpRequest.getEmail());

        if (user.isPresent()) {
            return SignUpResponse.builder()
                    .message("Email already exists.")
                    .build();
        }

        userRepository.save(UserEntity.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build());

        return SignUpResponse.builder()
                .message("Sign-up is successful.")
                .build();
    }

    public LoginResponse login(final LoginRequest userLogin) throws Exception {
        Optional<UserEntity> email = userRepository
                .findByEmail(userLogin.getEmail());
        if (!email.isPresent()) {
            return LoginResponse.builder()
                    .message("User not registered with provided email")
                    .build();

        } else if (email.isPresent()) {
            UserEntity userEntity = email.get();
            if (!userEntity.getPassword().equals(userLogin.getPassword())) {
                return LoginResponse.builder()
                        .message("Enter valid password to continue")
                        .build();
            }
        }
        return LoginResponse.builder()
                .message("User login successful")
                .build();
    }

    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        profileRepository.save(ProfileEntity.builder()
                .name(profileRequest.getName())
                .mobileNumber(profileRequest.getMobileNumber())
                .email(profileRequest.getEmail())
                .age(profileRequest.getAge())
                .gender(profileRequest.getGender())
                .dateOfBirth(profileRequest.getDateOfBirth())
                .address(profileRequest.getAddress())
                .panNumber(profileRequest.getPanNumber())
                .aadharNumber(profileRequest.getAadharNumber())
                .build());

        return ProfileResponse.builder()
                .message("Profile Created.")
                .build();

    }
}