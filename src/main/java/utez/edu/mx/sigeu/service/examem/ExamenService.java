package utez.edu.mx.sigeu.service.examem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.examen.ExamenRepository;
import utez.edu.mx.sigeu.model.person.Person;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ExamenService {
    private final ExamenRepository repository;

    public ExamenService(ExamenRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(
                repository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Examen examen){
        LocalDateTime now = LocalDateTime.now();
        examen.setCreatedAt(now);
        examen.setCode(RandomTwoLetter());

        // Asegurar que cada pregunta esté asociada con este examen y cada respuesta con su pregunta.
        for (Pregunta pregunta : examen.getPreguntas()) {
            pregunta.setExamen(examen); // Asociar el examen a la pregunta
            for (Respuesta respuesta : pregunta.getRespuestas()) {
                respuesta.setPregunta(pregunta); // Asociar la pregunta a la respuesta
            }
        }

        Optional<Examen> foundExamen = repository.findByCode(examen.getCode());
        if (foundExamen.isEmpty()){
            examen.setCode(RandomTwoLetter());
        }
        // Ahora al guardar el examen, JPA debería persistir las preguntas y respuestas con las claves foráneas correctas
        return new ResponseEntity<>(new ApiResponse(
                repository.saveAndFlush(examen),
                HttpStatus.OK
        ),HttpStatus.OK);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Examen> foundExamen =  repository.findById(id);
        if (foundExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                repository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> detele(@PathVariable Long id){
        Optional<Examen> foundExamen =  repository.findById(id);
        if (foundExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.BAD_REQUEST);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "Examen Eliminado"
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findByCode(String code){
       Optional<Examen> foundExamen = repository.findByCode(code);
       if (foundExamen.isEmpty())
           return new ResponseEntity<>(new ApiResponse(
                   HttpStatus.NOT_FOUND,
                   true,
                   "IdCodeNotFoundedExamen"
           ),HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(new ApiResponse(
               repository.findByCode(code),
               HttpStatus.OK
       ),HttpStatus.OK);
    }

    public String RandomTwoLetter(){
        String tmp = "ABCDEFGHIJKLMNOPKRSTUVWXYZ123456789";

        Random random = new Random();
        return tmp.charAt(random.nextInt(tmp.length())) +
                "" + tmp.charAt(random.nextInt(tmp.length()))
                + "" +tmp.charAt(random.nextInt(tmp.length()))
                + "" +tmp.charAt(random.nextInt(tmp.length()))
                + "" +tmp.charAt(random.nextInt(tmp.length()))
                + "" +tmp.charAt(random.nextInt(tmp.length()))
                ;
    }


}
