package utez.edu.mx.sigeu.controller.person;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.person.PersonDto.PersonDto;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.service.person.PersonService;

@Controller
@RequestMapping("/api/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return personService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody PersonDto personDto){
        return personService.register(personDto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return personService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return personService.findById(id);
    }
}
