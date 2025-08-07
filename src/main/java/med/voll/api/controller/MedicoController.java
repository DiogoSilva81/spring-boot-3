package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemMedico>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        Optional<Medico> medicoOptional = repository.findById(dados.id());
        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            medico.atualizarInformacoes(dados);
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable long id){
        Optional<Medico> medicoOptional = repository.findById(id);
        if(medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            medico.excluir();
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable long id){
        Optional<Medico> medicoOptional = repository.findById(id);
        if(medicoOptional.isPresent()){
            var medico = medicoOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

