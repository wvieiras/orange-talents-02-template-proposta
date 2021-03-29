package br.com.treino.proposta.proposta;

import br.com.treino.proposta.integracao.AnaliseFinanceiraCliente;
import br.com.treino.proposta.integracao.SolicitacaoAnaliseFinanceiraRequest;
import br.com.treino.proposta.integracao.SolicitacaoAnaliseFinanceiraResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private AnaliseFinanceiraCliente analiseCliente;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> post(@RequestBody @Valid NovaPropostaRequest request) {


        if(repository.existsByDocumento(request.getDocumento())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "proposta já existe para o cliente");

        }

        //grava no banco
        Proposta proposta = request.toModel();
        manager.persist(proposta);

        //submete para analise
        PropostaStatus status = subindoParaAnalise(proposta);
        proposta.updateStatus(status);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        headers.setLocation(location);
        return ResponseEntity.created(location).build();
    }

    private PropostaStatus subindoParaAnalise(Proposta proposta) {
        try{
            SolicitacaoAnaliseFinanceiraRequest req = new SolicitacaoAnaliseFinanceiraRequest(proposta);
            SolicitacaoAnaliseFinanceiraResponse response = analiseCliente.analisefinanceira(req);
            return response.toModel();

        } catch (FeignException.UnprocessableEntity e){
            return PropostaStatus.NÃO_ELEGIVEL;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado");
        }

    }

}
