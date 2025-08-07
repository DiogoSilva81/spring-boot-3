package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemPaciente>>listar(Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPaciente dados){
        Optional<Paciente> pacienteOptional = repository.findById(dados.id());
        if (pacienteOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            paciente.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable long id){
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if (optionalPaciente.isPresent()){
            Paciente paciente = optionalPaciente.get();
            paciente.excluir();
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable long id){
        Optional<Paciente> pacienteOptional = repository.findById(id);
        if (pacienteOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
}
