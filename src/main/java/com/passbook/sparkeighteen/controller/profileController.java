package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.ProfileRequest;
import com.passbook.sparkeighteen.peristence.POJO.ProfileResponse;
import com.passbook.sparkeighteen.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1/user")
@RestController
public class profileController {
    public final ProfileService profileService;

    public profileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation("To create user profile")
    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody final ProfileRequest profile) throws Exception {
        return ResponseEntity.ok(profileService.createProfile(profile));

    }
}
