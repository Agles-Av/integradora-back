package utez.edu.mx.sigeu.model.examen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen,Long> {
    Optional<Examen> findByCode (String code);
}
