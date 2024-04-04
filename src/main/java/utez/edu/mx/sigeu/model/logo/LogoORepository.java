package utez.edu.mx.sigeu.model.logo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoORepository extends JpaRepository<Logo, Long> {
    Optional<Logo> findByLogo(String logo);
}
