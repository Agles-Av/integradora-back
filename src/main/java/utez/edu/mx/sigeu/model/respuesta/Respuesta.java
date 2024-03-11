package utez.edu.mx.sigeu.model.respuesta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "respuesta")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 256,nullable = false)
    private String nombre;
    @Column
    private boolean correcta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    @JsonIgnore
    @OneToMany(mappedBy = "respuesta", cascade = CascadeType.PERSIST)
    private List<RespuestaUsuario> respuestaUsuario;

    public Respuesta(Long id, String nombre, boolean correcta, Pregunta pregunta) {
        this.id = id;
        this.nombre = nombre;
        this.correcta = correcta;
        this.pregunta = pregunta;
    }
}
