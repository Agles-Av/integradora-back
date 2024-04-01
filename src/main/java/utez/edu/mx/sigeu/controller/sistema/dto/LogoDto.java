package utez.edu.mx.sigeu.controller.sistema.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.logo.Logo;

@NoArgsConstructor
@Getter
@Setter
public class LogoDto {
    private Long id;
    private String logo;

    public Logo toEntity(){
        return new Logo(id,logo);
    }
}
