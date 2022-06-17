package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
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
}