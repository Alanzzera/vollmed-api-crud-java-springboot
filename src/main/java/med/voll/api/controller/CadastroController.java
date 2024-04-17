package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import med.voll.api.usuario.DadosAutenticacao;
import med.voll.api.usuario.Usuario;
import med.voll.api.usuario.UsuarioRepository;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Operation(description = "Cadastra usuário e criptografa a senha")
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody DadosAutenticacao dados) {
        // Criptografa a senha antes de salvar
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dados.login());
        novoUsuario.setSenha(passwordEncoder.encode(dados.senha()));
        
        novoUsuario = usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}