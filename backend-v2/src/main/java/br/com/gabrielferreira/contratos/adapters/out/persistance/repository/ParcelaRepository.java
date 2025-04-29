package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParcelaRepository extends JpaRepository<ParcelaEntity, UUID> {
}
