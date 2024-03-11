package utez.edu.mx.sigeu.controller.respuesta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

@NoArgsConstructor
@Getter
@Setter
public class RespuestaDto {
    private Long id;
    private String nombre;
    private boolean correcta;
    private Pregunta pregunta;

    public Respuesta toEntity(){
        return new Respuesta(id,nombre,correcta,pregunta);
    }
}
