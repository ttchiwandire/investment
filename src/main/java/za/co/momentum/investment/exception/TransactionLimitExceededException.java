package za.co.momentum.investment.exception;

public class TransactionLimitExceededException extends RuntimeException {
    public TransactionLimitExceededException(final String message) {
        super(message);
    }
}
