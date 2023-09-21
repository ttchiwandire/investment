package za.co.momentum.investment.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.co.momentum.investment.controller.InvestorController;
import za.co.momentum.investment.controller.WithdrawalController;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RestControllerAdvice(assignableTypes = {InvestorController.class, WithdrawalController.class})
public class GlobalExceptionHandler {

    private static final String ERROR = "Error";
    private static final String STACK_TRACE = "Stack=Trace";

    @ExceptionHandler(value = {TransactionNotAllowedException.class, InsufficientFundsException.class, TransactionLimitExceededException.class})
    public ResponseEntity<ErrorMessage> transationNotAllowedException(Exception ex) {
        log.error("{} {}", kv(ERROR, ex.getMessage()), kv(STACK_TRACE, ex.getStackTrace()));
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex) {
        log.error("{} {}", kv(ERROR, ex.getMessage()), kv(STACK_TRACE, ex.getStackTrace()));
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
