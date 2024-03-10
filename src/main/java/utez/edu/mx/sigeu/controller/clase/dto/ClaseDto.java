package utez.edu.mx.sigeu.controller.clase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.clase.Clase;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClaseDto {
    private Long id;
    private String name;
    private Usuario usuario;
    private List<Examen> examen;

    public Clase toEntity(){
        return new Clase(id,name,usuario,examen);
    }
}
