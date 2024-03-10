package utez.edu.mx.sigeu.model.estado_examen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "estado_examen")
public class EstadoExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40,nullable = false)
    private String name_state;

    @JsonIgnore
    @OneToMany(mappedBy = "examen",cascade = CascadeType.PERSIST)
    private List<Examen> examen;

    public EstadoExamen(Long id, String name_state) {
        this.id = id;
        this.name_state = name_state;
    }
}
