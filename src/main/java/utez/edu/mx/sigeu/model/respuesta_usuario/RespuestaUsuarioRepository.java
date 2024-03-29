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

    @Query(value = "select * from respuesta_usuario where id_usuario = :idUSer",nativeQuery = true)
    List<RespuestaUsuario> findRespuestaByIdUsuario (@Param("idUSer") Long idUSer );

    @Query(value = "SELECT ru.correcta FROM respuesta_usuario ru " +
            "JOIN pregunta p ON ru.id_pregunta = p.id " +
            "JOIN examen e ON p.id_examen = e.id " +
            "WHERE ru.id_usuario = :idusuario AND e.id = :examenid", nativeQuery = true)
    List<Boolean> findRespuestaByUsuarioAndExamen(@Param("idusuario") Long idusuario, @Param("examenid") Long examenid);

}
