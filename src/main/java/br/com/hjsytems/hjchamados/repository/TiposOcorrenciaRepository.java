
package br.com.hjsytems.hjchamados.repository;

import br.com.hjsytems.hjchamados.entity.TiposOcorrencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heugenio
 */
@Repository
public interface TiposOcorrenciaRepository extends JpaRepository<TiposOcorrencia, Integer> {
    
    List<TiposOcorrencia> findBydescricaoContaining(String descricao);
    
}
