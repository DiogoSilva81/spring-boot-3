package med.voll.api.domain.paciente;

public record DadosListagemPaciente (
        long id,
        String nome,
        String email,
        String telefone) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}
