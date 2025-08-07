package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
    }

    public void atualizarInformacoes(Endereco dados) {
        if (dados.getLogradouro() != null) {
            this.logradouro = dados.getLogradouro();
        }
        if (dados.getBairro() != null) {
            this.bairro = dados.getBairro();
        }
        if (dados.getCep() != null) {
            this.cep = dados.getCep();
        }
        if (dados.getUf() != null) {
            this.uf = dados.getUf();
        }
        if (dados.getCidade() != null) {
            this.cidade = dados.getCidade();
        }
        if (dados.getNumero() != null) {
            this.numero = dados.getNumero();
        }
        if (dados.getComplemento() != null) {
            this.complemento = dados.getComplemento();
        }
    }
}
