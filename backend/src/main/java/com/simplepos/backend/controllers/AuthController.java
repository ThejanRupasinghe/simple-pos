package com.simplepos.backend.controllers;

import com.simplepos.backend.models.Role;
import com.simplepos.backend.models.User;
import com.simplepos.backend.repository.UserRepository;
import com.simplepos.backend.security.jwt.JwtProvider;
import com.simplepos.backend.security.jwt.JwtResponse;
import com.simplepos.backend.security.details.SignInDetails;
import com.simplepos.backend.security.details.SignUpDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInDetails loginRequest,
                                              HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        Cookie cookie = new Cookie("foo","bar");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(55464956);
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.addHeader("Cache-Control", "cache, store, max-age=0, must-revalidate");
        response.addCookie(cookie);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDetails signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            if("admin".equals(role)){
                roles.add(Role.ROLE_ADMIN);
            } else if("user".equals(role)){
                roles.add(Role.ROLE_USER);
            } else {
                throw new RuntimeException("Fail! -> Cause: User Role not find.");
            }

        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}
