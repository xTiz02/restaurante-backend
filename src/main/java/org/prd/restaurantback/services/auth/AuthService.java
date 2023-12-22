package org.prd.restaurantback.services.auth;

import org.prd.restaurantback.dtos.SignupRequest;
import org.prd.restaurantback.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
