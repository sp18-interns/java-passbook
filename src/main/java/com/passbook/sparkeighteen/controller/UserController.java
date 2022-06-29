package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.*;
import com.passbook.sparkeighteen.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Sign-Up the user to passbook")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid final SignUpRequest request) {
        return new ResponseEntity<>(userService.signUp(request), HttpStatus.OK);
    }

    @ApiOperation("login the user to passbook")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest request) throws Exception {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @ApiOperation("Update user profile")
    @PutMapping("/user/{userID}/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Integer userID, @Valid @RequestBody final ProfileRequest request) throws Exception {
        return new ResponseEntity<>(userService.updateProfile(userID, request),HttpStatus.OK);
    }

}
