package br.com.gabrielferreira.contratos.adapters.out.persistance.repository;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, UUID> {

    @Query(
            "SELECT p FROM UsuarioEntity u " +
                    "JOIN u.perfis p " +
                    "WHERE u.id = :usuarioId "
    )
    List<PerfilEntity> findAllByUsuarioId(@Param("usuarioId") UUID usuarioId);

    @Query(
            "SELECT p FROM PerfilEntity p " +
                    "ORDER BY p.descricao ASC"
    )
    List<PerfilEntity> findAllOrderByDescricaoAsc();
}
