package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findByEmail(String email);
}
