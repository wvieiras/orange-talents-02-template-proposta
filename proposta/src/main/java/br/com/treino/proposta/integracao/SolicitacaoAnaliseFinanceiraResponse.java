package br.com.treino.proposta.integracao;

import br.com.treino.proposta.proposta.PropostaStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoAnaliseFinanceiraResponse {

    private String idProposta;

    @JsonProperty("resultadoSolicitacao")
    private String status;

    public String getIdProposta() {
        return idProposta;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SolicitacaoAnaliseFinanceiraResponse{" +
                "idProposta='" + idProposta + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public PropostaStatus toModel() {
        if ("COM_RESTRICAO".equals(status)){
            return PropostaStatus.NÃO_ELEGIVEL;
        }
        return PropostaStatus.ELEGIVEL;
    }
}
