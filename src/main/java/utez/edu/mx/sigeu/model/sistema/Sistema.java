package utez.edu.mx.sigeu.model.sistema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sistema")
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 15)
    private String color1;
    @Column(nullable = false,length = 15)
    private String color2;
    @Column(nullable = false,length = 15)
    private String color3;
}
