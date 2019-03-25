
package br.com.hjsystems.hjchamados.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
    

    public List<LinkedHashMap<String,Object>> listaUsuarioFornecedor() {
        LinkedHashMap<String,Object> itens;
        List<LinkedHashMap<String,Object>> valores = new LinkedList<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT US.USRS_ID AS 'ID_USUARIO', ");
        sql.append("    US.USRS_NOME AS 'NOME DO USUARIO', ");
        sql.append("    GRU.GPRO_NOME AS 'NOME DO GRUPO', ");
        sql.append("    PER.PERM_NOME AS 'PERMISSAO ROLES', ");
        sql.append("    UNI.UNEM_NOME ");
        sql.append(" FROM usuario_grupo UG ");
        sql.append(" INNER JOIN USUARIOS US ON(UG.codigo_usuario = US.USRS_ID) ");
        sql.append(" INNER JOIN GRUPO GRU ON(UG.codigo_grupo = GRU.GRPO_CODIGO) ");
        sql.append(" INNER JOIN grupo_permissao GP ON(GRU.GRPO_CODIGO = GP.codigo_grupo) ");
        sql.append(" INNER JOIN PERMISSAO PER ON(GP.codigo_permissao = PER.PERM_CODIGO) ");
        sql.append(" INNER JOIN \"ÜNIDADES_EMPRESARIAIS\" UNI ON(UNI.UNEM_ID = US.UNEM_ID) ");
        sql.append(" WHERE GRU.GPRO_NOME = 'Fornecedor'; ");
        
        Query query = em.createNativeQuery(sql.toString());
        List<Object[]> dados = query.getResultList();
        
        for(Object[] ob : dados) {
            itens = new LinkedHashMap<>();
            itens.put("idUsuario", Long.valueOf(ob[0].toString()));
            itens.put("nomeUsuario", ob[1].toString());
            itens.put("nomeGrupo", ob[2].toString());
            itens.put("permissoes", ob[3].toString());
            valores.add(itens);
        }
        return valores;
    }
}
