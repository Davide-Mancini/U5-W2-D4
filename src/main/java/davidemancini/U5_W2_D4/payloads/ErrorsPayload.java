package davidemancini.U5_W2_D4.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime oraErrore;
}
