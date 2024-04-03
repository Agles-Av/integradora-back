package utez.edu.mx.sigeu.model.usuario_examen;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioExamenRepository extends JpaRepository<UsuarioExamen,Long> {
List<UsuarioExamen> findAllByUsuario_Id(Long id);

Optional<UsuarioExamen> findByUsuarioAndExamen(Usuario idUsuario, Examen idExamen);
}
