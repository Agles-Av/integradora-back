package utez.edu.mx.sigeu.service.respuesta_usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta.RespuestaRepository;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuarioRepository;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class RespuestaUsuarioService {
    private final RespuestaUsuarioRepository repository;
    private final RespuestaRepository respuestaRepository;

    public RespuestaUsuarioService(RespuestaUsuarioRepository repository, RespuestaRepository respuestaRepository) {
        this.repository = repository;
        this.respuestaRepository = respuestaRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(
                repository.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<RespuestaUsuario>  foundRespuestaUsu = repository.findById(id);
        if (foundRespuestaUsu.isEmpty())
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

    @Transactional(rollbackFor = {SQLDataException.class})
    public ResponseEntity<ApiResponse> save (RespuestaUsuario usuario){
       if (usuario.getPregunta().isTipo()){
           Respuesta respuesta = respuestaRepository.findRespuestaCorrectaByPreguntaId(usuario.getPregunta().getId());
              if (respuesta.getId() == usuario.getRespuesta().getId()) {
                  usuario.setCorrecta(true);
              }else {
                  usuario.setCorrecta(false);
              }
       }

        return new ResponseEntity<>(new ApiResponse(
                repository.saveAndFlush(usuario),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> deleteFindById(@PathVariable Long id){
        Optional<RespuestaUsuario>  foundRespuestaUsu = repository.findById(id);
        if (foundRespuestaUsu.isEmpty())
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.NOT_FOUND,
                    true,
                    "IdNotFound"
            ),HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(
                HttpStatus.OK,
                false,"UsuarioEliminado"
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public  ResponseEntity<ApiResponse> findExamenHecho (Long id){
        return new ResponseEntity<>(new ApiResponse(
                repository.findExamenCodesByUsuario(id),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

}
