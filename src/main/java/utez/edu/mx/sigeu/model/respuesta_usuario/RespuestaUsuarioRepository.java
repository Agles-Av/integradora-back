package utez.edu.mx.sigeu.model.respuesta_usuario;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RespuestaUsuarioRepository extends JpaRepository<RespuestaUsuario,Long> {
    @Query(value = "SELECT examen.code " +
            "FROM examen " +
            "JOIN pregunta ON examen.id = pregunta.id_examen " +
            "JOIN respuesta_usuario ON respuesta_usuario.id_pregunta = pregunta.id " +
            "WHERE respuesta_usuario.id_usuario = :idUsuario",
            nativeQuery = true)
    List<String> findExamenCodesByUsuario(@Param("idUsuario") Long idUsuario);
}
