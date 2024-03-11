package utez.edu.mx.sigeu.model.respuesta_usuario;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaUsuarioRepository extends JpaRepository<RespuestaUsuario,Long> {
}
