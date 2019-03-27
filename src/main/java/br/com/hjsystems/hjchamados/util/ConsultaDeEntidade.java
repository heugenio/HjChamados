package br.com.hjsystems.hjchamados.util;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @author Hallef
 */
public final class ConsultaDeEntidade {
    
    private List<Object> dados;
    private List<Object> colunas;
    private String nomeTabela;
    
    public List<Object> retornaListaComValores() throws IllegalAccessException {
        return dados;
    }
    
    public List<Object> retornaListaComNomesColunas() {
        return colunas;
    }
    
    public String getNomeTabela() {
        return nomeTabela;
    }
    
    public void realizarBusca(Object oEntidade) throws IllegalAccessException {
        dados = new LinkedList<>();
        colunas = new LinkedList<>();
        Class entidade = oEntidade.getClass();//entidadeAnotada.getAnnotation(Entidade.class);
        Table table = (Table) entidade.getAnnotation(Table.class);
        nomeTabela = table.name();
        Field[] fields = getFieldsAnotados(entidade);
        for (Field field : fields) {
            field.setAccessible(true);
            Column col = field.getAnnotation(Column.class);

            if (col != null) {
                dados.add(field.get(oEntidade));
                colunas.add(col.name());
            }

        }
    }

    private Field[] getFieldsAnotados(Class<?> c) {
        if (c.getSuperclass() != null) {
            Field[] superClassFields = getFieldsAnotados(c.getSuperclass());
            Field[] thisFields = c.getDeclaredFields();
            Field[] allFields = new Field[superClassFields.length + thisFields.length];
            System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
            System.arraycopy(thisFields, 0, allFields, superClassFields.length, thisFields.length);
            return allFields;
        } else {
            return c.getDeclaredFields();
        }
    }
}
