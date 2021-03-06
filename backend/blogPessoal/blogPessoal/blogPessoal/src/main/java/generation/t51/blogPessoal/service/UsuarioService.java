package generation.t51.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import generation.t51.blogPessoal.model.Usuario;
import generation.t51.blogPessoal.model.UsuarioLogin;
import generation.t51.blogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent())
			return Optional.empty();
		usuario.setPassword(criptografarSenha(usuario.getPassword()));
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsername(usuario.getUsername());
			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			usuario.setPassword(criptografarSenha(usuario.getPassword()));
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		return Optional.empty();
	}	

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByUsername(usuarioLogin.get().getUsername());
		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getPassword(), usuario.get().getPassword())) {
	
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setName(usuario.get().getName());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsername(), usuarioLogin.get().getPassword()));
				usuarioLogin.get().setPassword(usuario.get().getPassword());

				return usuarioLogin;
			}
		}	
		return Optional.empty();
	}

	private String criptografarSenha (String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
}
