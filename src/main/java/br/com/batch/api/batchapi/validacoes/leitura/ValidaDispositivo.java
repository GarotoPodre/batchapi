package br.com.batch.api.batchapi.validacoes.leitura;

import br.com.batch.api.batchapi.dto.LeituraDto;
import br.com.batch.api.batchapi.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDispositivo implements ValidaLeitura {

    @Autowired
    DispositivoRepository dispositivoRepository;

    @Override
    public void valida(LeituraDto leituraDto) {
        var disp = dispositivoRepository.existsByIdLogger(leituraDto.idLogger());
    }
}
