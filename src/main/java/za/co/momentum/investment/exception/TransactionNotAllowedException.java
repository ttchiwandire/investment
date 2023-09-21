package za.co.momentum.investment.exception;

public class TransactionNotAllowedException extends RuntimeException {

    public TransactionNotAllowedException(final String message) {
        super(message);
    }
}
