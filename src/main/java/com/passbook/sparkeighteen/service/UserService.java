package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.ProfileRequest;
import com.passbook.sparkeighteen.peristence.POJO.ProfileResponse;
import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.ProfileRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserService(final UserRepository userRepository, final ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public LoginResponse login(final LoginRequest userLogin) throws Exception {
        Optional<UserEntity> optionalUser = userRepository
                .findByEmail(userLogin.getEmail());
        if (optionalUser.isEmpty()) {
            return LoginResponse.builder()
                    .message("User not registered with provided email")
                    .build();
        } else {
            UserEntity userEntity = optionalUser.get();
            if (!userEntity.getPassword().equals(userLogin.getPassword())) {
                return LoginResponse.builder()
                        .message("Enter valid password to continue")
                        .build();
            }
        }
//
        UserEntity user = optionalUser.get();
        ProfileEntity profile = profileRepository.findByUser(user);
        return LoginResponse.builder()
                .userID(user.getId())
                .profileID(profile.getId())
                .message("User login successful")
                .build();
    }

    public SignUpResponse signUp(@NonNull final SignUpRequest request) throws Exception {

        final Optional<UserEntity> user =
                userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            return SignUpResponse.builder()
                    .message("Email already exists.")
                    .build();
        }

        UserEntity savedUser = userRepository.save(UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .gender(request.getGender())
                .dob(request.getDob())
                .email(request.getEmail())
                .password(request.getPassword())
                .build());

        ProfileEntity profile = profileRepository.save(ProfileEntity.builder()
                .user(savedUser)
                .age(calculateAge(savedUser.getDob()))
                .build());


        return SignUpResponse.builder()
                .userID(profile.getUser().getId())
                .profileID(profile.getId())
                .message("Sign-up is successful.")
                .build();
    }

    private Integer calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public ProfileResponse getProfile(Integer profileID) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(profileID);
        if (optionalProfile.isEmpty()) {
            return ProfileResponse.builder()
                    .message("Profile doesn't exist. Please attempt Sign-Up.")
                    .build();
        }

        return ProfileResponse.builder()
                .profile(optionalProfile.get())
                .message("Profile fetch successful")
                .build();
    }

    public ProfileResponse updateProfile(Integer profileID, ProfileRequest request) {
        ProfileResponse response = this.getProfile(profileID);
        Optional<ProfileEntity> optionalProfile = Optional.ofNullable(response.getProfile());

        if (optionalProfile.isEmpty()) {
            response.setMessage("ProfileID incorrect. Please send appropriate profileID or register");
            return response;
        }

        // Updating existing user extracted with GetProfile
        ProfileEntity profile = optionalProfile.get();
        profile.setAadhar(request.getAadhar());
        profile.setPan(request.getPan());
        profile.setMobileNumber(request.getMobileNumber());
        profile.setAddress(request.getAddress());

        profileRepository.save(profile);

        return ProfileResponse.builder()
                .profile(profile)
                .message("Profile Updated Successfully")
                .build();
    }
}
