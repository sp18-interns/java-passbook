package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void validPayload_successResponse_signUpSuccessful() throws Exception {
        SignUpRequest signUpRequest  = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        SignUpResponse signUpResponse = userService.signUp(signUpRequest);
        assertTrue(signUpResponse.getMessage().equals("Sign-up is successful."));

    }

    @Test
    void validInput_userExists_signUpUnSuccessful() throws Exception {
        SignUpRequest signUpRequest  = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("")
                .build();

        Optional<UserEntity> userEntity = Optional.of(UserEntity.builder().build());
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(userEntity);

        SignUpResponse signUpResponse = userService.signUp(signUpRequest);
        assertTrue(signUpResponse.getMessage().equals("Email already exists."));

    }
}