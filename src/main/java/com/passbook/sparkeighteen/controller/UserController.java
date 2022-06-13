package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.SignUpRequest;
import com.passbook.sparkeighteen.peristence.POJO.SignUpResponse;
import com.passbook.sparkeighteen.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    private UserService userService;

    public UserController(final UserService userService){
        this.userService = userService;
    }

    @ApiOperation("sign-up the user to passbook")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody final SignUpRequest signUpRequest) throws Exception {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

}
