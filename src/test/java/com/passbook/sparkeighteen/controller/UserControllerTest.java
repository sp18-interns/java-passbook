package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith({MockitoExtension.class})
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void validRequest_successfulResponse_SignupSuccessfull() throws Exception {

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        Mockito.when(userService.signUp(any())).thenReturn(SignUpResponse.builder()
                .message("ok")
                .userID(1)
                .build());

        ResponseEntity<SignUpResponse> response = userController.signUp(signUpRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void validRequest_errorResponse_SignupSuccessful() throws Exception {

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@gmail.com")
                .password("")
                .build();

        Mockito.when(userService.signUp(any())).thenReturn(SignUpResponse.builder()
                .message("OK")
                .userID(1)
                .build());
        ResponseEntity<SignUpResponse> response = userController.signUp(signUpRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void validRequest_successfulResponse_LoginSuccessfull() throws Exception {

        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        Mockito.when(userService.login(any())).thenReturn(LoginResponse.builder()
                .message("ok")
                .userID(1)
                .build());

        ResponseEntity<LoginResponse> response = userController.login(loginRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void validRequest_errorResponse_LoginSuccessfull() throws Exception {

        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

        Mockito.when(userService.login(any())).thenReturn(LoginResponse.builder()
                .message("ok")
                .userID(1)
                .build());

        ResponseEntity<LoginResponse> response = userController.login(loginRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    /**
     * @throws Exception user deleted with userId.
     */
    @Test
    public void deleteProfile_validUserId_deleteProfileSuccessful() throws Exception {

        Integer userID = 1;

        Mockito.when(userService.deleteProfile(any())).thenReturn("user deleted " + userID);

        ResponseEntity<String> response = userController.deleteProfile(userID);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    /**
     * @throws Exception user id is not found.
     */
    @Test
    public void deleteProfile_userIdNotExist_deleteProfileUnsuccessful() throws Exception {

        Integer userID = 1;

        Mockito.when(userService.deleteProfile(any())).thenReturn("user id is not found");

        ResponseEntity<String> response = userController.deleteProfile(userID);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}