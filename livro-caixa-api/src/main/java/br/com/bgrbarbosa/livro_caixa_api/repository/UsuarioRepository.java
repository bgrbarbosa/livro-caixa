package br.com.bgrbarbosa.livro_caixa_api.repository;


import br.com.bgrbarbosa.livro_caixa_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

}
