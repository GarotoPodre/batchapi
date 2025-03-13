package br.com.batch.api.batchapi.repository;

import br.com.batch.api.batchapi.domain.Dispositivo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    /**
     * Verifica a existência do dispositivo.
     * @param idLogger
     * @return
     */
    boolean existsByIdLogger(String idLogger);
}
