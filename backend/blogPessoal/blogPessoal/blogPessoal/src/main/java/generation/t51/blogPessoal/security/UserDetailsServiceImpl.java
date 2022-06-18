package generation.t51.blogPessoal.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import generation.t51.blogPessoal.model.Usuario;
import generation.t51.blogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = userRepository.findByUsername(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
		return usuario.map(UserDetailsImpl::new).get();
	}
}
