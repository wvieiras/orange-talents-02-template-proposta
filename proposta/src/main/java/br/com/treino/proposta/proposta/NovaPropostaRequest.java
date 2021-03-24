package br.com.treino.proposta.proposta;

import br.com.treino.proposta.validacao.CPFouCNPJ;
import br.com.treino.proposta.validacao.UniqueValue;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

   //@UniqueValue(domainClass = Proposta.class, fieldName = "documento")
   @NotBlank
   @CPFouCNPJ
   private String documento;

   @Email
   @NotBlank
   private String email;

   @NotBlank
   private String nome;

   private String endereco;

   @Positive
   @NotNull
   private BigDecimal salario;


    public NovaPropostaRequest(@NotBlank String documento, @Valid @Email @NotBlank String email, @Valid @NotBlank String nome, String endereco, @Valid @Positive @NotNull BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    //Getters
    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel(){
       return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
   }

}
