package br.com.batch.api.batchapi.controller;

import br.com.batch.api.batchapi.domain.Leitura;
import br.com.batch.api.batchapi.dto.LeituraDto;
import br.com.batch.api.batchapi.repository.LeituraRepository;
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
    private LeituraRepository repository;

    /**
     * Usado para inserção de leituras manuais
     * @param leitura
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid LeituraDto leitura){
        var l = new Leitura(leitura);
        repository.save(l);

        return ResponseEntity.ok(leitura);

    }

    @GetMapping
    public ResponseEntity <Page<LeituraDto>> leituras(@PageableDefault(size = 30, sort={"data"})Pageable paginacao){
        var page = repository.findAll(paginacao).map(LeituraDto::new);
        return ResponseEntity.ok(page);
    }
}
