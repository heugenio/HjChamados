
package br.com.hjsystems.hjchamados.dao;

import br.com.hjsystems.hjchamados.util.ConsultaDeEntidade;
import br.com.hjsystems.hjchamados.util.SITUACAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hallef
 */
@Repository
public class ConsultaEntidadeDao {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public Enum consultaEntidade(Object oEntidade) throws IllegalAccessException {
        ConsultaDeEntidade ce = new ConsultaDeEntidade();
        StringBuilder sql = new StringBuilder();
        ce.realizarBusca(oEntidade);
        Enum situacao = SITUACAO.SEM_ALTERACAO;
        //String situacao = "";
        if(ce.retornaListaComValores().get(0) == null) {
            sql.append(" SELECT ").append(ce.retornaListaComNomesColunas().get(0));
            sql.append(" FROM ").append(ce.getNomeTabela());
            sql.append(" WHERE ").append(ce.retornaListaComNomesColunas().get(1)).append(" = ").append("'").append(ce.retornaListaComValores().get(1)).append("'");
            for(int i=2; i<ce.retornaListaComNomesColunas().size(); i++) {
                sql.append(" AND ").append(ce.retornaListaComNomesColunas().get(i)).append(" = ").append("'").append(ce.retornaListaComValores().get(i)).append("'");
            }
            Query query = em.createNativeQuery(sql.toString());
            try {
                query.getSingleResult();
                //Tentativa de inserir o mesmo registro
                //situacao = "MESMO_REGISTRO";
                situacao = SITUACAO.MESMO_REGISTRO;
            }catch(EmptyResultDataAccessException | NoResultException | NonUniqueResultException  ex) {
                situacao = SITUACAO.NOVO_REGISTRO;
                //situacao = "NOVO_REGISTRO";
            }
        } else if(Integer.valueOf(ce.retornaListaComValores().get(0).toString()) > 0) {
            sql.append("SELECT ");
            for (int i = 0; i < ce.retornaListaComNomesColunas().size(); i++) {

                if (i < ce.retornaListaComNomesColunas().size() - 1) {
                    sql.append(ce.retornaListaComNomesColunas().get(i)).append(",");
                } else {
                    sql.append(ce.retornaListaComNomesColunas().get(i));
                }
            }
            sql.append(" FROM ").append(ce.getNomeTabela());
            sql.append(" WHERE ").append(ce.retornaListaComNomesColunas().get(1)).append(" = ").append("'").append(ce.retornaListaComValores().get(1)).append("'");
            for(int i=2; i<ce.retornaListaComNomesColunas().size(); i++) {
                sql.append(" AND ").append(ce.retornaListaComNomesColunas().get(i)).append(" = ").append("'").append(ce.retornaListaComValores().get(i)).append("'");
            }
            Query query = em.createNativeQuery(sql.toString());
            List<Object[]> dados = query.getResultList();
            if(dados.size() > 0) {
                int qtdVezesIguais = 0;
                for (Object[] ob : dados) {
                    for(int j=0; j<ob.length; j++) {
                        if(ob[j] instanceof String) {
                            if(ob[j].toString().equalsIgnoreCase(ce.retornaListaComValores().get(j).toString())) {
                                qtdVezesIguais++;
                            }
                        } else if(ob[j] instanceof Integer) {
                            if(Integer.parseInt(ob[j].toString()) == Integer.parseInt(ce.retornaListaComValores().get(j).toString())) {
                                qtdVezesIguais++;
                            }
                        } else if(ob[j] instanceof Long) {
                            if(Long.parseLong(ob[j].toString()) == Long.parseLong(ce.retornaListaComValores().get(j).toString())) {
                                qtdVezesIguais++;
                            }
                        } else if(ob[j] instanceof java.util.Date) {
                            if(ob[j] == (java.util.Date) ce.retornaListaComValores().get(j)) {
                                qtdVezesIguais++;
                            }
                        } else if(ob[j] instanceof java.sql.Date) {
                            if(ob[j] == (java.sql.Date) ce.retornaListaComValores().get(j)) {
                                qtdVezesIguais++;
                            }
                        }
                    }
                }
                if(qtdVezesIguais == ce.retornaListaComValores().size()) situacao = SITUACAO.SEM_ALTERACAO ; //situacao = "SEM_ALTERACAO";
            } else {
                sql = new StringBuilder();
                sql.append(" SELECT ").append(ce.retornaListaComNomesColunas().get(0));
                sql.append(" FROM ").append(ce.getNomeTabela());
                sql.append(" WHERE ").append(ce.retornaListaComNomesColunas().get(1)).append(" = ").append("'").append(ce.retornaListaComValores().get(1)).append("'");
                for(int i=2; i<ce.retornaListaComNomesColunas().size(); i++) {
                    sql.append(" AND ").append(ce.retornaListaComNomesColunas().get(i)).append(" = ").append("'").append(ce.retornaListaComValores().get(i)).append("'");
                }
                query = em.createNativeQuery(sql.toString());
                try {
                    query.getSingleResult();
                    situacao = SITUACAO.MESMO_REGISTRO;
                    //situacao = "MESMO_REGISTRO";
                }catch(EmptyResultDataAccessException | NoResultException | NonUniqueResultException  ex) {
                    situacao = SITUACAO.NOVO_REGISTRO;
                    //situacao = "NOVO_REGISTRO";
                }
            }
        }
        
        return situacao;
    }

}
