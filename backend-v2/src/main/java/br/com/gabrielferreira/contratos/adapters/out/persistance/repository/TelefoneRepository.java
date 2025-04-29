package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneEntity, UUID> {

    Optional<TelefoneEntity> findByIdAndUsuarioId(UUID id, UUID idUsuario);
}
