package davidemancini.U5_W2_D4.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMessage;
    public ValidationException(List<String> errorsMessage)
    {
        super("ci sono errori di validazione");
        this.errorsMessage=errorsMessage;
    }
}
