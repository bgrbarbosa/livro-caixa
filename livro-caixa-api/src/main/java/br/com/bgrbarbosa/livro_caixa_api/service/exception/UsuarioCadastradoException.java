package br.com.bgrbarbosa.livro_caixa_api.service.exception;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException( String login ){
        super("Usuário já cadastrado para o login " + login);
    }
}
