package utez.edu.mx.sigeu.service.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.config.ApiResponse;
import utez.edu.mx.sigeu.controller.auth.dto.SignedDto;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.security.jwt.JwtProvider;
import utez.edu.mx.sigeu.service.usuario.UsuarioService;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UsuarioService usuarioService;
    private final AuthenticationManager manager;
    private final JwtProvider provider;

    public AuthService(UsuarioService usuarioService, AuthenticationManager manager, JwtProvider provider) {
        this.usuarioService = usuarioService;
        this.manager = manager;
        this.provider = provider;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> signIn(String email,String password){
        try {
            Optional<Usuario> foundUser = usuarioService.findByEmail(email);
            if (foundUser.isEmpty())
                return new ResponseEntity<>(new ApiResponse(
                        HttpStatus.NOT_FOUND,
                        true,
                        "EmailNotFound"
                ), HttpStatus.NOT_FOUND);

            Usuario usuario = foundUser.get();
            System.out.println(usuario);
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = provider.generateToke(auth);

            SignedDto signedDto = new SignedDto(token,"Bearer",usuario,usuario.getRole());
            return new ResponseEntity<>(
                    new ApiResponse(signedDto, HttpStatus.OK),
                    HttpStatus.OK
            );

        }catch (Exception e){
            e.printStackTrace();
            String message = "CredentialMismatch";
            if (e instanceof DisabledException)
                message = "UserDisabled";
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.BAD_REQUEST, true, message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
