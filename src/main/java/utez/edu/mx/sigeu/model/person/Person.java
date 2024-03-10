package utez.edu.mx.sigeu.model.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.usuario.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 50,nullable = false)
    private String lastname;
    @Column(length = 50,nullable = false)
    private String surname;
    @Column(length = 20,unique = true)
    private String matricula;
    @Column(length = 20,nullable = false,unique = true)
    private String curp;

    @JsonIgnore
    @OneToOne(mappedBy = "person", cascade = CascadeType.PERSIST)
    private Usuario usuario;


    public Person(String name, String lastname, String surname, String matricula, String curp) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.matricula = matricula;
        this.curp = curp;
    }

    public Person(Long id, String name, String lastname, String surname, String matricula, String curp) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
        this.matricula = matricula;
        this.curp = curp;
    }


}
