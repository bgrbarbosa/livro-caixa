package br.com.bgrbarbosa.livro_caixa_api.service.exception;

public class UserException extends RuntimeException {

    public UserException(String login ){
        super("Usuário já cadastrado para o login " + login);
    }
}
