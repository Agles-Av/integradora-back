package utez.edu.mx.sigeu.service.pregunta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.pregunta.Pregunta;
import utez.edu.mx.sigeu.model.pregunta.PreguntaRepository;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PreguntaService {
    private final PreguntaRepository repository;

    public PreguntaService(PreguntaRepository repository) {
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
    public ResponseEntity<ApiResponse> register (Pregunta pregunta){
        for (Respuesta respuesta : pregunta.getRespuestas()) {
            respuesta.setPregunta(pregunta); // Asociar la pregunta a la respuesta
        }
        return new ResponseEntity<>(new ApiResponse(
                repository.saveAndFlush(pregunta),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById (@PathVariable Long id){
        Optional<Pregunta> foundPregunta = repository.findById(id);
        if (foundPregunta.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ApiResponse(
                repository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> deleteById( @PathVariable Long id){
        Optional<Pregunta> foundPregunta = repository.findById(id);
        if (foundPregunta.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);

        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "PreguntaBorrada"
        ),HttpStatus.OK);
    }

}
