package utez.edu.mx.sigeu.service.sistema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.sistema.Sistema;
import utez.edu.mx.sigeu.model.sistema.SistemaRepository;

import java.sql.SQLException;
@Service
@Transactional
public class SistemaService {
    private final SistemaRepository repository;

    public SistemaService(SistemaRepository repository) {
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
    public ResponseEntity<ApiResponse> save (Sistema sistema){
        return new ResponseEntity<>(new ApiResponse(
                repository.save(sistema),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Sistema sistema){
        return new ResponseEntity<>(new ApiResponse(
                repository.save(sistema),
                HttpStatus.OK
        ),HttpStatus.OK);
    }




}
