package br.com.treino.proposta.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseFinanceiraCliente", url = "http://localhost:9999/")
public interface AnaliseFinanceiraCliente {

    @PostMapping("/api/solicitacao")
    public SolicitacaoAnaliseFinanceiraResponse analisefinanceira(@RequestBody SolicitacaoAnaliseFinanceiraRequest request);
}
