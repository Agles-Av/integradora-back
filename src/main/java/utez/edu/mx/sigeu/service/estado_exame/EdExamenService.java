package utez.edu.mx.sigeu.service.estado_exame;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamen;
import utez.edu.mx.sigeu.model.estado_examen.EstadoExamenRepository;

import java.sql.SQLException;

@Service
@Transactional
public class EdExamenService {
    private final EstadoExamenRepository repository;

    public EdExamenService(EstadoExamenRepository repository) {
        this.repository = repository;
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
}
