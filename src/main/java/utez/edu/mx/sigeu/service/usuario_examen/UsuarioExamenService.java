package utez.edu.mx.sigeu.service.usuario_examen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamenRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioExamenService {
    private final UsuarioExamenRepository repository;

    public UsuarioExamenService(UsuarioExamenRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAllByIdUser(Long id){
        List<UsuarioExamen> foundExamen = repository.findAllByUsuario_Id(id);
        if (foundExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "No Hay Examanes aún"
            ),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(
                repository.findAllByUsuario_Id(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(
                repository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<UsuarioExamen> foundUsuarioExamen = repository.findById(id);
        if (foundUsuarioExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    false,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(
                repository.findById(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save (UsuarioExamen usuarioExamen){
        return new ResponseEntity<>(new ApiResponse(
                repository.saveAndFlush(usuarioExamen),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        Optional<UsuarioExamen> foundUsuarioExamen = repository.findById(id);
        if (foundUsuarioExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    false,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,
                "UsuarioEliminado"
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(UsuarioExamen usuarioExamen){
        Optional<UsuarioExamen> foundUsuarioExamen = repository.findById(usuarioExamen.getId());
        if (foundUsuarioExamen.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    false,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse(
                repository.save(usuarioExamen),
                HttpStatus.OK
        ),HttpStatus.OK);
    }
}
