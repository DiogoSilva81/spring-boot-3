package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.Endereco;

public record DadosAtualizacaoMedico(
        @NotNull
        long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
}
