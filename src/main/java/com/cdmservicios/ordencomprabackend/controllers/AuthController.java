package com.cdmservicios.ordencomprabackend.controllers;

import com.cdmservicios.ordencomprabackend.core.requests.LoginRequest;
import com.cdmservicios.ordencomprabackend.core.requests.SignupRequest;
import com.cdmservicios.ordencomprabackend.core.messages.JwtResponse;
import com.cdmservicios.ordencomprabackend.core.messages.MessageResponse;
import com.cdmservicios.ordencomprabackend.models.ERole;
import com.cdmservicios.ordencomprabackend.models.Role;
import com.cdmservicios.ordencomprabackend.models.Usuario;
import com.cdmservicios.ordencomprabackend.repositories.RoleRepository;
import com.cdmservicios.ordencomprabackend.repositories.UsuarioRepository;
import com.cdmservicios.ordencomprabackend.security.keys.JwtUtils;
import com.cdmservicios.ordencomprabackend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final
    AuthenticationManager authenticationManager;

    final
    UsuarioRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/encoder/{pass}")
    public ResponseEntity<?> encoderPass(@PathVariable String pass) {
        return ResponseEntity.ok(encoder.encode(pass));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getIdusuario(),
                userDetails.getUsername(),
                userDetails.getNombreusuario(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsuario(signUpRequest.getUsuario())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El usuario ya existe!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este email ya esta en uso!"));
        }

        // Create new user's account
        Usuario user = new Usuario(
                signUpRequest.getUsuario(),
                signUpRequest.getNombreusuario(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "supp":
                        Role suppRole = roleRepository.findByName(ERole.ROLE_SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(suppRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario Registrado con Exito!"));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }

}
