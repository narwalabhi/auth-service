package com.narwal.authservice.controller;

import com.narwal.authservice.model.AuthenticationRequest;
import com.narwal.authservice.model.AuthenticationResponse;
import com.narwal.authservice.security.UserDetailService;
import com.narwal.authservice.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailService userDetailService;

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("controller");
        UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(),  userDetails.getAuthorities()));

//        try {
//            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        }
//        catch (BadCredentialsException e){
//            throw new Exception("Incorrect username or password", e);
//        }
//        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails, authentication.getAuthorities());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
