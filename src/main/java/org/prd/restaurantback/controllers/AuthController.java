package org.prd.restaurantback.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.prd.restaurantback.dtos.AuthenticationRequest;
import org.prd.restaurantback.dtos.AuthenticationResponse;
import org.prd.restaurantback.dtos.SignupRequest;
import org.prd.restaurantback.dtos.UserDto;
import org.prd.restaurantback.entities.User;
import org.prd.restaurantback.repositories.UserRepository;
import org.prd.restaurantback.services.auth.AuthService;
import org.prd.restaurantback.services.auth.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

//    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtService;
    private final UserRepository userRepository;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    public AuthController(AuthService authService, JwtUtil jwtService, UserRepository userRepository) {
        this.authService = authService;

        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDto userDto = authService.createUser(signupRequest);
        if(userDto!= null){
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }

        return new ResponseEntity<>("User not created, Come again later",HttpStatus.OK);


    }
    @GetMapping("/hellCheck")
    public ResponseEntity<?> hellCheck() {
        return new ResponseEntity<>("Todo bien",HttpStatus.OK);
    }

    /*@PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authentication,HttpServletResponse response) throws Exception {
        try{
            System.out.println(authentication.getUsername());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword()));
            //authenticationManager autentica el usuario y contraseña con el userDetailsService
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }catch (DisabledException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }
        System.out.println("llego");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getUsername());

        final String jwt = jwtService.generateToken(userDetails.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }*/

}
