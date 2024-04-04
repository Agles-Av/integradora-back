package utez.edu.mx.sigeu.model.examen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {
    Optional<Examen> findByCode (String code);
}
