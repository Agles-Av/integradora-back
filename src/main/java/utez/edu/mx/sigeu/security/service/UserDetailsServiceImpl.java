package utez.edu.mx.sigeu.security.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.sigeu.model.usuario.Usuario;
import utez.edu.mx.sigeu.security.entity.UserDetailsImpl;
import utez.edu.mx.sigeu.service.usuario.UsuarioService;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl
        implements UserDetailsService {

    private final UsuarioService service;

    public UserDetailsServiceImpl(UsuarioService service) {
        this.service = service;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> foundUser = service.findByEmail(username);
        System.out.println(foundUser.toString());
        if (foundUser.isPresent())
            return UserDetailsImpl.build(foundUser.get());
        throw new UsernameNotFoundException("UserNotFound");
    }//agles la mas foxi de todas
}
