package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.SaldoTotalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaldoTotalRepository extends JpaRepository<SaldoTotalEntity, UUID> {
}
