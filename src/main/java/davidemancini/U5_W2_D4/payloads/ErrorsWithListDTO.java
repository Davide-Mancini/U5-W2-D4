package davidemancini.U5_W2_D4.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(String message, LocalDateTime timeStamp, List<String> errorsList) {
}
