package com.cstapin.auth.ui;

import com.cstapin.auth.domain.UserPrincipal;
import com.cstapin.auth.oauth2.github.GithubCodeRequest;
import com.cstapin.auth.service.AuthService;
import com.cstapin.auth.service.dto.*;
import com.cstapin.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login/github")
    public ResponseEntity<LoginResponse> loginFromGithub(@Valid @RequestBody GithubCodeRequest request) {
        LoginResponse response = authService.loginFromGithub(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/join/admin")
    public ResponseEntity<Void> joinAdmin(@Valid @RequestBody JoinRequest request) {

        authService.join(request, Member.MemberRole.ADMIN);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissueToken(@Valid @RequestBody ReissueTokenRequest requestBody) {

        TokenResponse response = authService.reissueToken(requestBody);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/join/user")
    public ResponseEntity<Void> joinUser(@Valid @RequestBody JoinRequest request) {

        authService.join(request, Member.MemberRole.USER);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Void> withdrawMember(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        authService.withdrawMember(userPrincipal.getUsername());
        return ResponseEntity.ok().build();
    }
}