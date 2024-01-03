package org.prd.restaurantback.services.auth;

import jakarta.annotation.PostConstruct;
import org.prd.restaurantback.dtos.SignupRequest;
import org.prd.restaurantback.dtos.UserDto;
import org.prd.restaurantback.entities.User;
import org.prd.restaurantback.enums.UserRole;
import org.prd.restaurantback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@PostConstruct se ejecuta despues de que se crea el bean en el contenedor de spring y despues de que se inyecten las dependencias
    public void createAdminAccount() {
        User admin = userRepository.findByRole(UserRole.ADMIN);
        if(admin == null){
           User user = new User();
                user.setName("admin");
                user.setEmail("admin@gmail.com");
                user.setPassword(bCryptPasswordEncoder.encode("admin"));
                user.setRole(UserRole.ADMIN);
                userRepository.save(user);
        }
    }
    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setName(createdUser.getName());
        userDto.setEmail(createdUser.getEmail());
        userDto.setRole(createdUser.getRole());

        return userDto;
    }
}
