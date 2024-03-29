package utez.edu.mx.sigeu.model.clase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clase")
public class Clase {
    //datos de Clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 30)
    private String name;

    //realciones


    @ManyToOne
    @JoinColumn(name = "id_usuario")

    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "clase", cascade = CascadeType.PERSIST)
    private List<Examen> examen;


    public Clase(Long id, String name, Usuario usuario, List<Examen> examen) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
        this.examen = examen;
    }
}
