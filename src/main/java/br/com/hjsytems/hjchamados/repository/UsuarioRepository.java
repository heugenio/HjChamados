
package br.com.hjsytems.hjchamados.repository;

import br.com.hjsytems.hjchamados.entity.Usuarios;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heugenio
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
    List<Usuarios> findByNomeContaining(String nome);
    
    Optional<Usuarios> findByLogin(String login);
    
    @Query("select distinct p.nome from Usuarios u inner join u.grupos g inner join g.permissoes p where u = ?1")
    List<String> permissoes(Usuarios oEUsuarios);

}
