
package br.com.hjsystems.hjchamados.security;

import br.com.hjsytems.hjchamados.entity.Usuarios;
import br.com.hjsytems.hjchamados.repository.UsuarioRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hallef
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
    @Autowired private UsuarioRepository iUsuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuarios> usuarioOptional = iUsuarioRepository.findByLogin(login);
        Usuarios oEUsuarios = usuarioOptional.orElseThrow(()-> new UsernameNotFoundException("Usuário ou Senha inválidos!"));
        return new User(oEUsuarios.getLogin(), oEUsuarios.getSenha(), getPermissoes(oEUsuarios));
    }
    
    private Collection<? extends GrantedAuthority> getPermissoes(Usuarios oEUsuarios) {
        Set<SimpleGrantedAuthority> authoritys = new HashSet<>();
        
        List<String> listaPermissoes = iUsuarioRepository.permissoes(oEUsuarios);
        listaPermissoes.forEach(p -> authoritys.add(new SimpleGrantedAuthority("ROLE_"+p.toUpperCase())));

        return authoritys;
    }
    
}
