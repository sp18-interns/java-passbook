package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import lombok.NonNull;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignUpResponse signUp(@NonNull final SignUpRequest signUpRequest) throws Exception {
        final Optional<UserEntity> byEmail =
                userRepository.findByEmail(signUpRequest.getEmail());
        if (byEmail.isPresent()) {
            return SignUpResponse.builder()
                    .message("Email already exists.")
                    .build();
        }

        UserEntity save = userRepository.save(UserEntity.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build());

        return SignUpResponse.builder()
                .message("Sign-up is successful.")
                .build();
    }

    public LoginResponse login(final LoginRequest loginRequest) throws Exception {


        final Optional<UserEntity> byEmailAndPassword =
                userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (byEmailAndPassword.isPresent()) {
            return LoginResponse.builder()
                    .message("User login successful")
                    .build();
        } else {
            final Optional<UserEntity> byEmail =
                    userRepository.findByEmail(loginRequest.getEmail());
            if (byEmail.isEmpty()) {
                return LoginResponse.builder()
                        .message("Enter valid email")
                        .build();
            } else {
                final Optional<UserEntity> byPassword =
                        userRepository.findByPassword(loginRequest.getPassword());
                if (byPassword.isEmpty()) {
                    return LoginResponse.builder()
                            .message("Your password is wrong enter valid password and try again")
                            .build();
                }
            }
            return LoginResponse.builder()
                    .message("User not found goto sign-up")
                    .build();
        }
    }
}