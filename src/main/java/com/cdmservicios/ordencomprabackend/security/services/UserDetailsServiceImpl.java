package com.cdmservicios.ordencomprabackend.security.services;

import com.cdmservicios.ordencomprabackend.models.Usuario;
import com.cdmservicios.ordencomprabackend.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;

    public UserDetailsServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = repository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado al Usuario: " + usuario));
        return UserDetailsImpl.build(user);
    }

    public Usuario findByEmail(String usuario){
        return repository.findByEmail(usuario).orElseThrow(null);
    }
}
