package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void validRequest_successfulResponse_SignupSuccessfull() throws Exception {

        SignUpRequest signUpRequest  = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        ResponseEntity<SignUpResponse> response = userController.signUp(signUpRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void validRequest_errorResponse_SignupSuccessful() throws Exception {

        SignUpRequest signUpRequest  = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        ResponseEntity<SignUpResponse> response = userController.signUp(signUpRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}