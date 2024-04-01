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


    public Sistema(Long id, String color1, String color2, String color3) {
        this.id = id;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

    public Sistema(String color1, String color2, String color3) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

}
