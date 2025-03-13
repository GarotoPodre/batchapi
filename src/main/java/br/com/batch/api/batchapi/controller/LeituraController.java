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


@RestController
@RequestMapping("/leituras")
public class LeituraController {


    @Autowired
    private EfetivaLeitura efetivaLeitura;
    @Autowired
    private LeituraRepository repository;

    /**
     * Usado para inserção de leituras manuais
     * @param leitura
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid LeituraDto leitura){

        LeituraDto dto = efetivaLeitura.efetiva(leitura);

        return ResponseEntity.ok(leitura);

    }

    /**
     * Retorna a leitura mensal (30 dias)
     * @param paginacao
     * @return
     */
    @GetMapping
    public ResponseEntity <Page<LeituraDto>> leituras(@PageableDefault(size = 30, sort={"data"})Pageable paginacao){
        var page = repository.findAll(paginacao).map(LeituraDto::new);
        return ResponseEntity.ok(page);
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
