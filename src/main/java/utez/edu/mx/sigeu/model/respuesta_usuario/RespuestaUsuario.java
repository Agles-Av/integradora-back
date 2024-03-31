package utez.edu.mx.sigeu.model.respuesta_usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.usuario.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "respuesta_usuario")
public class RespuestaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean correcta;
    @Column(length = 128)
    private String description;


    @ManyToOne
    @JoinColumn(name = "id_respuesta")
    private Respuesta respuesta;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    public RespuestaUsuario(Long id, boolean correcta, String description, Respuesta respuesta, Usuario usuario, Pregunta pregunta) {
        this.id = id;
        this.correcta = correcta;
        this.description = description;
        this.respuesta = respuesta;
        this.usuario = usuario;
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "RespuestaUsuario{" +
                "id=" + id +
                ", respuesta=" + respuesta +
                ", pregunta=" + pregunta +
                '}';
    }
}
