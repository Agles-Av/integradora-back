package utez.edu.mx.sigeu.model.sistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema,Long> {
   Optional<Sistema> findByColor1(String color1);
   /* Optional<Sistema> findByColor2(String color1);
    Optional<Sistema> findByColor3(String color1);*/
}
