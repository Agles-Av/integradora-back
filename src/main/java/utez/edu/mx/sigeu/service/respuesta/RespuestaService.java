package utez.edu.mx.sigeu.service.respuesta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta.RespuestaRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class RespuestaService {
    private final RespuestaRepository repository;

    public RespuestaService(RespuestaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(
                repository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save (Respuesta respuesta){
        return new ResponseEntity<>(new ApiResponse(
                repository.saveAndFlush(respuesta),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        Optional<Respuesta> foundRespuesta = repository.findById(id);
        if (foundRespuesta.isEmpty())
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.BAD_REQUEST,true,"IdNotFound"
        ),HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "RespuestaEliminada"
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Respuesta> foundRespuesta = repository.findById(id);
        if (foundRespuesta.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,true,"IdNotFound"
            ),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(
                repository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }



}
