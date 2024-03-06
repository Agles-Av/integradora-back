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
    @Column(length = 128,nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_respuesta",unique = true)
    private Respuesta respuesta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario",unique = true)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_pregunta", unique = true)
    private Pregunta pregunta;
}
