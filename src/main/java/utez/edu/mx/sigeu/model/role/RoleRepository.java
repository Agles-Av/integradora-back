package utez.edu.mx.sigeu.model.role;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

}
