package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import med.voll.api.infra.TokenService;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.usuario.DadosAutenticacao;
import med.voll.api.usuario.Usuario;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService tokenService;
	
	@Operation(description = "Efetua login e retorna um token JWT")
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(authenticationToken);
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}
