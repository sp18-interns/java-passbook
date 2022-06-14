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

    public LoginResponse login (final LoginRequest loginRequest) throws Exception {
        final Optional<UserEntity> byEmail =
                userRepository.findByEmail(loginRequest.getEmail());
        if (byEmail.isPresent()) {
            return LoginResponse.builder()
                    .message(" user login successful")
                    .build();
        }
        UserEntity save = userRepository.save(UserEntity.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build());

        return LoginResponse.builder()
                .message("user not found goto singup")
                .build();
    }


}
