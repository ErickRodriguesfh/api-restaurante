package br.ebr.apirestaurante.domain.exception;

public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BaseException(String mensagem) {
        super(mensagem);
    }

    public BaseException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
