package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoEntity, UUID> {
}
