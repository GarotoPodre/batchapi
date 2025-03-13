package br.com.batch.api.batchapi.validacoes;

import br.com.batch.api.batchapi.domain.Leitura;
import br.com.batch.api.batchapi.dto.LeituraDto;
import br.com.batch.api.batchapi.repository.LeituraRepository;
import br.com.batch.api.batchapi.validacoes.leitura.ValidaLeitura;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe que aplica as validações e faz a gravação dos dodos
 */
@Service
public class EfetivaLeitura {

    @Autowired
    private LeituraRepository repository;

    @Autowired
    private List<ValidaLeitura> validadores;

    public LeituraDto efetiva(@Valid LeituraDto leitura) {

        //Validando regras de negócio
        validadores.forEach(validador -> validador.valida(leitura));
        var l = new Leitura(leitura);
        repository.save(l);

       return leitura;

    }
}
