package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.Endereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        long id,
        String nome,
        String telefone,
        String email,
        Endereco endereco
) {
}
