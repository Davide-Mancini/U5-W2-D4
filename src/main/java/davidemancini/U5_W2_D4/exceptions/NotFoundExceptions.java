package davidemancini.U5_W2_D4.exceptions;

import java.util.UUID;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(UUID id) {
        super("Il blog con id " + id +" non è stato trovato");
    }
}
