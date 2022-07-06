package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.LoginRequest;
import com.passbook.sparkeighteen.peristence.POJO.LoginResponse;
import com.passbook.sparkeighteen.peristence.POJO.ProfileRequest;
import com.passbook.sparkeighteen.peristence.POJO.ProfileResponse;
import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type User controller is responsible for processing incoming REST API request, preparing model and returning view to be rendered in response.
 */
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * This is controller for managing user signUp Login and profile part.
     * @param userService in this file we write business logic for all API.
     */
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Sign up response entity.
     *
     * @param request the request for user details for signup and create profile.
     * @return the response entity returns success message and create user profile.
     */
    @ApiOperation("Sign-Up the user to passbook")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody final SignUpRequest request) {
        return new ResponseEntity<>(userService.signUp(request), HttpStatus.OK);
    }

    /**
     * Login response entity.
     *
     * @param request the request for user credentials(Email and password).
     * @return the response entity returns login success message and user profile.
     * @throws Exception the exception handle bad request or any type of wrong input/values.
     */
    @ApiOperation("login the user to passbook")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest request) throws Exception {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    /**
     * Update profile response entity.
     *
     * @param userID  using userID we update userProfile.
     * @param request the request for user details to update their profile.
     * @return the response entity returns the updated profile.
     * @throws Exception the exception handle bad request or any type of wrong input/values.
     */
    @ApiOperation("Update user profile")
    @PutMapping("/user/{userID}/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Integer userID, @Valid @RequestBody final ProfileRequest request) throws Exception {
        return new ResponseEntity<>(userService.updateProfile(userID, request), HttpStatus.OK);
    }

    /**
     * @param userId to find particular user
     * @return user is deleted or user id is not found.
     */
    @ApiOperation("delete user profile")
    @DeleteMapping("/user/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable final Integer userId) {
        return new ResponseEntity<>(userService.deleteProfile(userId), HttpStatus.OK);
    }

}
