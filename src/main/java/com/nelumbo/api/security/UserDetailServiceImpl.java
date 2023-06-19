package com.nelumbo.api.security;

import com.nelumbo.api.converter.UsuarioConverter;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.entity.Rol;
import com.nelumbo.api.entity.Usuario;
import com.nelumbo.api.repository.RolRepository;
import com.nelumbo.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioConverter  usuarioConverter;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));

        return new UserDetailsImpl(usuario);
    }

    public String obtenerRolUsuarioActual() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String email = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));

        // Obtener el rol asociado al usuario
        Rol rol = rolRepository.findById(usuario.getRol().getId())
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el rol del usuario"));

        return rol.getNombre();
    }

    public UsuarioDTO obtenerUsuarioLogueado() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));

        return usuarioConverter.usuarioToUsuarioDTO(usuario);
    }


}