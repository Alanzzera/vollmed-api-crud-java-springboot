package med.voll.api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // Método para buscar o usuário por login
    UserDetails findByLogin(String login);
    
    // Método para verificar se o login já existe
    boolean existsByLogin(String login);
}
