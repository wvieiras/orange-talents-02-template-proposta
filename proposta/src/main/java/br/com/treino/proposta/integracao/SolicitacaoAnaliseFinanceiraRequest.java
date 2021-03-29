package br.com.treino.proposta.integracao;

import br.com.treino.proposta.proposta.Proposta;

public class SolicitacaoAnaliseFinanceiraRequest {

    private String documento;

    private String nome;

    private String idProposta;


    public SolicitacaoAnaliseFinanceiraRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "SolicitacaoAnaliseFinanceiraRequest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
