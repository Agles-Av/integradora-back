package utez.edu.mx.sigeu.controller.examen.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.clase.Clase;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ExamenDto {
    private Long id;
    private String  title;
    private String description;
    private String code;
    private Clase clase;
    private EstadoExamen examen;
    private List<Pregunta> preguntas;


    public Examen toEntity(){
        return new Examen(id,title,description,code,clase,examen,preguntas);
    }
}
