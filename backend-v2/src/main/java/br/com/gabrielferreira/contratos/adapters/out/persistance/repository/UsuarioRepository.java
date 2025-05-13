package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    @Query(
            "SELECT u FROM UsuarioEntity u " +
                    "LEFT JOIN FETCH u.saldoTotal s " +
                    "LEFT JOIN FETCH u.perfis p " +
                    "WHERE u.email = :email "
    )
    Optional<UsuarioEntity> findByEmail(@Param("email") String email);

    @Query(
            "SELECT u FROM UsuarioEntity u " +
                    "LEFT JOIN FETCH u.saldoTotal s " +
                    "LEFT JOIN FETCH u.perfis p " +
                    "WHERE u.id = :id "
    )
    Optional<UsuarioEntity> findUsuarioById(@Param("id") UUID id);
}
