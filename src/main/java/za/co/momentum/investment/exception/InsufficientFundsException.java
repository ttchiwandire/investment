package za.co.momentum.investment.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(final String message){
        super(message);
    }
}
