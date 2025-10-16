package davidemancini.U5_W2_D4.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AutoriPayload {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
}
