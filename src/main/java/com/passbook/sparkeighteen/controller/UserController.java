package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.entity.ProfileEntity;
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
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Sign-Up the user to passbook")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody final SignUpRequest request) throws Exception {
        SignUpResponse response = userService.signUp(request);
        if (response.getUserID() != null)
            return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("login the user to passbook")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody final LoginRequest request) throws Exception {
        LoginResponse response = userService.login(request);
        if (response.getUserID() != null)
            return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
