package org.prd.restaurantback.controllers;

import org.prd.restaurantback.dtos.SignupRequest;
import org.prd.restaurantback.dtos.UserDto;
import org.prd.restaurantback.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDto userDto = authService.createUser(signupRequest);
        if(userDto!= null){
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }

        return new ResponseEntity<>("User not created, Come again later",HttpStatus.OK);


    }
    @GetMapping("/login")
    public ResponseEntity<?> loginUser() {
        return new ResponseEntity<>("Login exitoso",HttpStatus.OK);
    }
}
