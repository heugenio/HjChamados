
package br.com.hjsytems.hjchamados.repository;

import br.com.hjsytems.hjchamados.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hallef
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
    
}
