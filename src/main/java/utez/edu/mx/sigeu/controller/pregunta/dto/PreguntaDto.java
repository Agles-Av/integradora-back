package utez.edu.mx.sigeu.controller.pregunta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PreguntaDto {
    private Long id;
    private String name;
    private boolean tipo;
    private Examen examen;


    public Pregunta toEntity(){
        return new Pregunta(id,name,tipo,examen);
    }
}
