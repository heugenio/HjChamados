
package br.com.hjsystems.hjchamados.util;

/**
 *
 * @author Hallef
 */
public enum SITUACAO {
    MESMO_REGISTRO("MESMO_REGISTRO"),
    NOVO_REGISTRO("NOVO_REGISTRO"),
    SEM_ALTERACAO("SEM_ALTERACAO");
    
    private String situacao;

    private SITUACAO(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

}
