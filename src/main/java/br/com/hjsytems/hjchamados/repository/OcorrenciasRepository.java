
package br.com.hjsytems.hjchamados.repository;

import br.com.hjsytems.hjchamados.entity.Ocorrencias;
import br.com.hjsytems.hjchamados.entity.UnidadesEmpresariais;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heugenio
 */
@Repository
public interface OcorrenciasRepository extends JpaRepository<Ocorrencias, Integer> {
    
    //lista por fornecedor
    @Query("select o from Ocorrencias o join o.fornecedor f where f.nome like %?1% and o.status = ?2")
    List<Ocorrencias> findByFornecedorStatus(String nome, String status);
    
    //lista por unidade
    @Query("select o from Ocorrencias o join o.fornecedor f where f.nome like %?1% and o.status = ?2 and o.unidadeEmpresarial = ?3")
    List<Ocorrencias> findByForStatusUnidade(String nome, String status, UnidadesEmpresariais oEmpresariais);
}
