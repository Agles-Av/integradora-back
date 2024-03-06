package utez.edu.mx.sigeu.model.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

public interface RespuestaRepository extends JpaRepository<RespuestaUsuario, Long> {
}
