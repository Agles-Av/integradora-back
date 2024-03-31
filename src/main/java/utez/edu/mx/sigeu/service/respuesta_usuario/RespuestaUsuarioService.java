package utez.edu.mx.sigeu.service.respuesta_usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.model.examen.Examen;
import utez.edu.mx.sigeu.model.examen.ExamenRepository;
import utez.edu.mx.sigeu.model.respuesta.Respuesta;
import utez.edu.mx.sigeu.model.respuesta.RespuestaRepository;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuario;
import utez.edu.mx.sigeu.model.respuesta_usuario.RespuestaUsuarioRepository;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamen;
import utez.edu.mx.sigeu.model.usuario_examen.UsuarioExamenRepository;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RespuestaUsuarioService {
    private final RespuestaUsuarioRepository repository;
    private final RespuestaRepository respuestaRepository;
    private final UsuarioExamenRepository usuarioExamenRepository;
    private final ExamenRepository examenRepository;

    public RespuestaUsuarioService(RespuestaUsuarioRepository repository, RespuestaRepository respuestaRepository, UsuarioExamenRepository usuarioExamenRepository, ExamenRepository examenRepository) {
        this.repository = repository;
        this.respuestaRepository = respuestaRepository;
        this.usuarioExamenRepository = usuarioExamenRepository;
        this.examenRepository = examenRepository;
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
    public ResponseEntity<ApiResponse> save(List<RespuestaUsuario> respuestas) {
        int correctas = 0;
        int incorrectas = 0;
        Usuario usuario = null;
        Examen examen = null;

        for (RespuestaUsuario respuestaUsuario : respuestas) {
            usuario = respuestaUsuario.getUsuario();
            Respuesta respuestaCorrecta = respuestaRepository.findRespuestaCorrectaByPreguntaId(respuestaUsuario.getPregunta().getId());

            if (respuestaUsuario.getPregunta().isTipo()) {
                if (respuestaCorrecta != null && respuestaCorrecta.getId().equals(respuestaUsuario.getRespuesta().getId())) {
                    respuestaUsuario.setCorrecta(true);
                    correctas++;
                } else {
                    respuestaUsuario.setCorrecta(false);
                    incorrectas++;
                }
            }

            repository.save(respuestaUsuario);
        }
        if (usuario != null) {
            Optional<Long> foundExamenUserId = repository.findExamenByIdUser(usuario.getId());
            if (foundExamenUserId.isPresent()) {
                Optional<Examen> foundExamen = examenRepository.findById(foundExamenUserId.get());
                if (foundExamen.isPresent()) {
                    examen = foundExamen.get();
                }
            }
        }
        if (usuario != null && examen != null) {
            saveInUsuarioExamen(usuario, examen, correctas, incorrectas);
        }

        return new ResponseEntity<>(new ApiResponse("Respuestas guardadas correctamente", HttpStatus.OK), HttpStatus.OK);
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

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findRespuestaByIdUsuario(@PathVariable Long idUsuario){
        return new ResponseEntity<>(new ApiResponse(
                repository.findRespuestaByIdUsuario(idUsuario),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findCorrectaRespuestaByUserIdAndEaxamenId( Long idUsuario,  Long idExamen){
        return new ResponseEntity<>(new ApiResponse(
                repository.findRespuestaByUsuarioAndExamen(idUsuario, idExamen),
                HttpStatus.OK
        ),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void saveInUsuarioExamen(Usuario usuario, Examen examen, int correctas, int incorrectas) {
        Optional<UsuarioExamen> existingRecord = usuarioExamenRepository.findByUsuarioAndExamen(usuario, examen);

        UsuarioExamen usuarioExamen;
        if (existingRecord.isPresent()) {
            // Si existe, actualiza el registro existente
            usuarioExamen = existingRecord.get();
        } else {
            // Si no existe, crea un nuevo registro
            usuarioExamen = new UsuarioExamen();
            usuarioExamen.setUsuario(usuario);
            usuarioExamen.setExamen(examen);
            usuarioExamen.setRespondido(true);
        }


        usuarioExamen.setCalificacion(calcularCalificacion(correctas, incorrectas));
        usuarioExamen.setEndAt(LocalDateTime.now());


        usuarioExamenRepository.save(usuarioExamen);
    }

    private int calcularCalificacion(int correctas, int incorrectas) {
        int totalPreguntas = correctas + incorrectas;
        double resultado;
        if (totalPreguntas > 0 && totalPreguntas < 10){
             resultado = (double) correctas / totalPreguntas * 10;
        }else {
             resultado = (double) correctas / totalPreguntas * 100;
        }
        return (int) resultado;
    }

}
