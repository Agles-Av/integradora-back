package utez.edu.mx.sigeu.controller.respuesta_usuario.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class RespuestaUsuarioDto {
    private Long id;
    private boolean correcta;
    private String description;
    private Respuesta respuesta;
    private Usuario usuario;
    private Pregunta pregunta;

    public RespuestaUsuario toEntity(){
        return new RespuestaUsuario(id,correcta,description,respuesta,usuario,pregunta);
    }

}
