package br.dev.joaquim.bank;

/**
 * @param message Mensagem detalhando a causa da exceção
 * 
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
