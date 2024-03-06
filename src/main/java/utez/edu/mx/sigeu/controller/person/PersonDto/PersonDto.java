package utez.edu.mx.sigeu.controller.person.PersonDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utez.edu.mx.sigeu.model.person.Person;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private String matricula;
    private String curp;

    public Person toEntity(){
        return new Person(id,name,lastname,surname,matricula,curp);
    }
}
