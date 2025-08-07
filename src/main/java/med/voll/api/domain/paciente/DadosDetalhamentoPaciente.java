package med.voll.api.domain.paciente;

import med.voll.api.endereco.Endereco;

public record DadosDetalhamentoPaciente(long id,
                                        String nome,
                                        String telefone,
                                        String emial,
                                        Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco());
    }
}
