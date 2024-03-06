package utez.edu.mx.sigeu.service.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.person.PersonRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    /*
    * Pendiente de mas servicios
    * */

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(
                personRepository.findAll(),HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Person person){
        Optional<Person> foundPersonMatricula = personRepository.findByMatricula(person.getMatricula());
        if (foundPersonMatricula.isPresent())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "MatriculaAlreadyExist"
            ),HttpStatus.BAD_REQUEST);
        Optional<Person> foundPersonCurp = personRepository.findByCurp(person.getCurp());
        if (foundPersonCurp.isPresent())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "CurpAlreadyExist"
            ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                personRepository.saveAndFlush(person),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Person> foundPerson = personRepository.findById(id);
        if (foundPerson.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "IdNotFound"
            ),HttpStatus.BAD_REQUEST);
        personRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                true,
                "Se elimino correctamente: " + foundPerson
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Person> foundPerson = personRepository.findById(id);
        if (foundPerson.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "PersonNotFound"
            ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                personRepository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }


}
