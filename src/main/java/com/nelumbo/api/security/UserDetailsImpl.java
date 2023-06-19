package com.nelumbo.api.security;
import java.util.Collection;
import java.util.Collections;
import com.nelumbo.api.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        super();
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return usuario.getPass();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public String getNombre() {
        return usuario.getNombre();
    }

}