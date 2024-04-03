package utez.edu.mx.sigeu.model.logo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.loadtime.definition.Definition;

@Entity
@Getter
@Setter
@Table(name = "logo")
@NoArgsConstructor
public class Logo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String logo;

    public Logo(Long id, String logo) {
        this.id = id;
        this.logo = logo;
    }

    public Logo(String logo) {
        this.logo = logo;
    }
}
