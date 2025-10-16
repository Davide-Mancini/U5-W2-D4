package davidemancini.U5_W2_D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AutoriDTO(
        @NotBlank(message = "il nome è obbligatorio")
        @Size(min = 2, max = 20, message = "il nome deve avere una lunghezza compresa tra 2 e 20")
        String nome,
        @NotBlank(message = "il cognome è obbligatorio")
        @Size(min = 2, max = 20, message = "il cognome deve avere una lunghezza compresa tra 2 e 20")
        String cognome,
        @NotBlank(message = "l'email è obbligatoria")
        @Email(message = "formato non corretto")
        String email,
        @NotNull(message = "data obbligatoria")
        LocalDate dataDiNascita) {
}
