package utez.edu.mx.sigeu.model.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

import java.util.List;
import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("SELECT r FROM Respuesta r WHERE r.pregunta.id = :preguntaId AND r.correcta = true")
    Respuesta findRespuestaCorrectaByPreguntaId(Long preguntaId);




}