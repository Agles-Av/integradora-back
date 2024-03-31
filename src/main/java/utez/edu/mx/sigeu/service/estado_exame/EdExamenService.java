package utez.edu.mx.sigeu.service.estado_exame;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamenRepository;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.examen.ExamenRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class EdExamenService {
    private final EstadoExamenRepository repository;
    private final ExamenRepository examenRepository;

    public EdExamenService(EstadoExamenRepository repository, ExamenRepository examenRepository) {
        this.repository = repository;
        this.examenRepository = examenRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> registe(EstadoExamen examen){
        return new ResponseEntity<>(new ApiResponse(
                repository.save(examen),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public  ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(new ApiResponse(
                repository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> changeState (Long idExamen, Long id){
        Optional<Examen> foundExamenbyId = examenRepository.findById(idExamen);
        if (foundExamenbyId.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdExamNotFound"
            ),HttpStatus.NOT_FOUND);
        Optional<EstadoExamen> foundEstadoExamen = repository.findById(id);
        if (foundEstadoExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        Examen examen = foundExamenbyId.get();
        EstadoExamen estadoExamen = foundEstadoExamen.get();
        examen.setExamen(estadoExamen);
        examenRepository.save(examen);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "EstadoExamenChanged"
        ),HttpStatus.OK);
    }
}
