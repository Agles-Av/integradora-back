package utez.edu.mx.sigeu.controller.sistema.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.sistema.Sistema;

@Getter
@Setter
@NoArgsConstructor
public class ColoresDto {
    private Long id;
    private String color1;
    private String color2;
    private String color3;

    public Sistema toEntity(){
        return new Sistema(id,color1, color2, color3);
    }
}
