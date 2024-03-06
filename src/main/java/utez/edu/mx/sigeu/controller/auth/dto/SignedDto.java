package utez.edu.mx.sigeu.controller.auth.dto;

import lombok.Value;
import utez.edu.mx.sigeu.model.role.Role;
import utez.edu.mx.sigeu.model.usuario.Usuario;

import java.util.List;

@Value
public class SignedDto {
    String token;
    String tokenType;
    Usuario user;
    Role roles;
}
