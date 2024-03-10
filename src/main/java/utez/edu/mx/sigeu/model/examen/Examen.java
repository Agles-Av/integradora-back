package utez.edu.mx.sigeu.model.examen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import utez.edu.mx.sigeu.model.clase.Clase;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "examen")
public class Examen {
    //datos de examen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String  title;
    @Column(length = 120, nullable = false)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endAt;
    @Column(length = 10,nullable = false)
    private String code;

    //relaciones

    @ManyToOne
    @JoinColumn(name = "id_clase")
    private Clase clase;


    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoExamen examen;

    @JsonIgnore
    @OneToMany(mappedBy = "examen", cascade = CascadeType.PERSIST)
    private List<UsuarioExamen> usuarioExamen;

    @JsonIgnore
    @OneToMany(mappedBy = "examen", cascade = CascadeType.PERSIST)
    private List<Pregunta> preguntas;

    public Examen(Long id, String title, String description, String code, Clase clase, EstadoExamen examen) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.code = code;
        this.clase = clase;
        this.examen = examen;
    }
}
