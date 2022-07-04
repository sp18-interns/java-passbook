package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.ProfileRequest;
import com.passbook.sparkeighteen.peristence.POJO.ProfileResponse;
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


/**
 * The type User controller test.
 */
@ExtendWith({MockitoExtension.class})
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    /**
     * Check whether the request is valid and having successfull response then the user signUp is successfull.
     *
     * @throws Exception the exception
     */
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

    /**
     * check whether request is valid and having error response then the Signup is SignupunSuccessful
     *
     * @throws Exception the exception
     */
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

    /**
     * when request is valid and having successful response then the login is successfull.
     *
     * @throws Exception the exception
     */
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

    /**
     * when request is valid and having error response then the login is unsuccessfull.
     *
     * @throws Exception the exception
     */
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
     * In this part we check the request is valid and getting successfull response then the Profile is Updated successfully.
     * @throws Exception when user is not find then it throws error user not find.
     */
    @Test
    void validRequest_successfulResponse_UserUpdateSuccessfull() throws Exception {
        ProfileRequest profileRequest = ProfileRequest.builder()
                .aadhar("123456554445")
                .pan("asdfghjdf")
                .address("koparkhairne")
                .mobileNumber("9022068607")
                .build();
        ResponseEntity<ProfileResponse> response = userController.updateProfile(1,profileRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    /**
     * When request is getting null then it throws error.
     * @throws Exception it throws error userID is null.
     */
    @Test
    void validRequest_errorResponse_UserUpdateUnSuccessfull() throws Exception {
        ProfileRequest profileRequest = ProfileRequest.builder()
                .aadhar("123456554445")
                .pan("asdfghjdf")
                .address("koparkhairne")
                .mobileNumber("9022068607")
                .build();
        ResponseEntity<ProfileResponse> response = userController.updateProfile(null,profileRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}