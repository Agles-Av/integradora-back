package utez.edu.mx.sigeu.controller.respuesta_usuario;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.respuesta_usuario.dto.RespuestaUsuarioDto;
import utez.edu.mx.sigeu.service.respuesta_usuario.RespuestaUsuarioService;

import java.util.List;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/usuariorespuesta")
public class UsuarioRespuestaController {
    private final RespuestaUsuarioService service;

    public UsuarioRespuestaController(RespuestaUsuarioService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody List<RespuestaUsuarioDto> dtos){
        dtos.forEach(dto -> service.save(dto.toEntity()));
        return new ResponseEntity<>(new ApiResponse("Respuestas guardadas correctamente", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/examenHecho/{id}")
    public ResponseEntity<ApiResponse> examenHecho(@PathVariable Long id){
        return service.findExamenHecho(id);
    }

}
