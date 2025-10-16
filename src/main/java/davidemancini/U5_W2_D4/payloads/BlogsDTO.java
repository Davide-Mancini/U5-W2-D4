package davidemancini.U5_W2_D4.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlogsDTO(
        @NotBlank(message = "inserisci la categoria")
        String categoria,
        @NotBlank(message = "titolo obbligatorio")
        String titolo,
        @NotBlank(message = "contenuto obbligatorio")
        String contenuto,
        @Min(1)
        int tempoDiLettura,
        @NotNull(message = "id dell'autore obbligatorio")
        UUID author_id
) {
}
