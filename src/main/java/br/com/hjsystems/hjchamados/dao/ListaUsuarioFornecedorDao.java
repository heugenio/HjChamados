
package br.com.hjsystems.hjchamados.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hallef
 */
@Repository
public class ListaUsuarioFornecedorDao {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public LinkedList<Map<String,Object>> listaUsuarioFornecedor() {
        Map<String,Object> itens = new LinkedHashMap<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT US.USRS_NOME AS 'NOME DO USUARIO', GRU.GPRO_NOME AS 'NOME DO GRUPO',PER.PERM_NOME AS 'PERMISSAO ROLES' ");
        sql.append(" FROM usuario_grupo UG ");
        sql.append(" INNER JOIN USUARIOS US ON(UG.codigo_usuario = US.USRS_ID) ");
        sql.append(" INNER JOIN GRUPO GRU ON(UG.codigo_grupo = GRU.GRPO_CODIGO) ");
        sql.append(" INNER JOIN grupo_permissao GP ON(GRU.GRPO_CODIGO = GP.codigo_grupo) ");
        sql.append(" INNER JOIN PERMISSAO PER ON(GP.codigo_permissao = PER.PERM_CODIGO) ");
        sql.append(" WHERE GRU.GPRO_NOME = 'Fornecedor' ");
        
        Query query = em.createNativeQuery(sql.toString());
        int lenLista = 0;
        while(lenLista < query.getResultList().size()) {
            System.out.println(query.getResultList().get(lenLista));
            lenLista++;
        }
        return null;
    }
    
}
