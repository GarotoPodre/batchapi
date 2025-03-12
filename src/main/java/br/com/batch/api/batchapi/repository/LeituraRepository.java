package br.com.batch.api.batchapi.repository;

import br.com.batch.api.batchapi.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {
}
