package utez.edu.mx.sigeu.service.clase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.clase.Clase;
import utez.edu.mx.sigeu.model.clase.ClaseRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class ClaseService {

    private final ClaseRepository claseRepository;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(
                claseRepository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Clase clase){
        clase = claseRepository.saveAndFlush(clase);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "ClaseRegistrada" + clase
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Clase> findById = claseRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        claseRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "Usuario Eliminado"
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findByID(@PathVariable Long id){
        Optional<Clase> findById = claseRepository.findById(id);
        if (findById.isEmpty())
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.BAD_REQUEST,
                true,
                "IdNotFdund"
        ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                claseRepository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(@RequestBody Clase clase){
        Optional<Clase> findById = claseRepository.findById(clase.getId());
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.BAD_REQUEST,
                    true,
                    "IdNotFound"
            ),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(
                claseRepository.save(clase),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

}
