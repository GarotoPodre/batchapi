package br.com.batch.api.batchapi.controller;

import br.com.batch.api.batchapi.domain.Leitura;
import br.com.batch.api.batchapi.dto.LeituraDto;
import br.com.batch.api.batchapi.repository.LeituraRepository;
import br.com.batch.api.batchapi.validacoes.EfetivaLeitura;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/leituras")
public class LeituraController {


    @Autowired
    private EfetivaLeitura efetivaLeitura;
    @Autowired
    private LeituraRepository repository;

    /**
     * Usado para inserção de leituras manuais, se tudo der certo, retorno código 201
     * @param leitura
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid LeituraDto leitura, UriComponentsBuilder uriBuilder){

        LeituraDto dto = efetivaLeitura.efetiva(leitura);
        System.out.println("dto :"+dto.toString());
        var uri=uriBuilder.path("/leituras/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }


    @GetMapping("/{id}")
    public ResponseEntity leitura(@PathVariable Long id ){
        var l=repository.getReferenceById(id);
        return ResponseEntity.ok(new LeituraDto(l));
    }

    @GetMapping
    public ResponseEntity <List<LeituraDto>>listagem(){
        List<Leitura> leituras=repository.findAll();
        List<LeituraDto> leiturasDto=leituras.stream().map(LeituraDto::new).toList();
        return ResponseEntity.ok(leiturasDto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar(@RequestBody @Valid LeituraDto leitura){
        var l=repository.getReferenceById(leitura.id());

        l.setValor(leitura.valor());
        l.setResponsavel(leitura.responsavel());

        //Não é necessário salvar, a JPA faz isso.
        return ResponseEntity.ok(new LeituraDto(l));
    }


}
