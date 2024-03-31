package utez.edu.mx.sigeu.model.estado_examen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoExamenRepository extends JpaRepository<EstadoExamen,Long> {
    Optional<EstadoExamen> findByName(String name);
}
