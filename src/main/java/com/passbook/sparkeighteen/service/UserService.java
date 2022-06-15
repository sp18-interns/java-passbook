package com.passbook.sparkeighteen.service;

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

    public SignUpResponse signUp(@NonNull final SignUpRequest userSignUp) throws Exception {
        final Optional<UserEntity> byEmail =
                userRepository.findByEmail(userSignUp.getEmail());
        if (byEmail.isPresent()) {
            return SignUpResponse.builder()
                    .message("Email already exists.")
                    .build();
        }
        UserEntity save = userRepository.save(UserEntity.builder()
                .email(userSignUp.getEmail())
                .password(userSignUp.getPassword())
                .build());
        return SignUpResponse.builder()
                .message("Sign-up is successful.")
                .build();
    }
}
