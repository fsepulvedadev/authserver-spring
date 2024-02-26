package com.fsepulvedadev.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsepulvedadev.models.ApplicationUser;
import com.fsepulvedadev.models.LoginResponseDTO;
import com.fsepulvedadev.models.Role;
import com.fsepulvedadev.repository.IRoleRepository;
import com.fsepulvedadev.repository.IUserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser (String username, String password) {
        String encondedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        
        Set<Role> authorities = new HashSet<Role>();

        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(0, username, encondedPassword, authorities));



    }

    public LoginResponseDTO loginUser (String user, String password) {


        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(user).get(), token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }

    }

}
