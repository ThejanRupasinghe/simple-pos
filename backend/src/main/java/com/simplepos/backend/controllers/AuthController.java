package com.simplepos.backend.controllers;

import com.simplepos.backend.details.ResponseDetails;
import com.simplepos.backend.models.Role;
import com.simplepos.backend.models.User;
import com.simplepos.backend.repository.UserRepository;
import com.simplepos.backend.security.jwt.JwtProvider;
import com.simplepos.backend.security.jwt.JwtResponse;
import com.simplepos.backend.details.SignInDetails;
import com.simplepos.backend.details.SignUpDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Maps the sign in request.
     *
     * @param loginRequest wrapper SignInDetails for the login JSON body
     * @param response
     * @return
     */
    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
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

        // TODO: 1/19/19 set the JWT in browser cookie
        Cookie cookie = new Cookie("foo", "bar");
        cookie.setHttpOnly(true);
        cookie.setPath("/api");
        response.addCookie(cookie);

        logger.info("Authentication success. username - " + loginRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Maps sign up request
     *
     * @param signUpRequest wrapper SignUpDetails for the sign up JSON body
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDetails signUpRequest, HttpServletRequest request) throws Exception {
        //username already in database
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logger.error("Given username already exists. username - " + signUpRequest.getUsername());
            ResponseDetails responseDetails = new ResponseDetails(new Date(), HttpServletResponse.SC_BAD_REQUEST, "Given username already exists.", "Existing User", request.getRequestURI());
            return new ResponseEntity<>(responseDetails.toJsonString(), HttpStatus.BAD_REQUEST);
        }

        //creates new user account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        //adds roles to the account
        for (String role : strRoles) {
            if ("admin".equals(role)) {
                roles.add(Role.ROLE_ADMIN);
            } else if ("user".equals(role)) {
                roles.add(Role.ROLE_USER);
            } else {
                //given user role not found in Role enum
                logger.error("Invalid user role " + role);
                ResponseDetails responseDetails = new ResponseDetails(new Date(),
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Given user role not found.", "Invalid role", request.getRequestURI());
                return new ResponseEntity<>(responseDetails.toJsonString(), HttpStatus.BAD_REQUEST);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);

        logger.info("User saved. username - " + user.getUsername() + ", roles - " + user.getRoles().toString());
        ResponseDetails responseDetails = new ResponseDetails(new Date(),
                HttpServletResponse.SC_OK,
                "User saved successfully.", null, request.getRequestURI());
        return new ResponseEntity<>(responseDetails.toJsonString(), HttpStatus.OK);
    }
}
