
package br.com.hjsystems.hjchamados.security;

import br.com.hjsytems.hjchamados.entity.Usuarios;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Hallef
 */
public class UsuarioSistema extends User{
    
    private Usuarios oEUsuarios;
    
    public UsuarioSistema(Usuarios oEUsuarios, Collection<? extends GrantedAuthority> authorities) {
        super(oEUsuarios.getLogin(), oEUsuarios.getSenha(), authorities);
        this.oEUsuarios = oEUsuarios;
    }

    public Usuarios getoEUsuarios() {
        return oEUsuarios;
    }
}
