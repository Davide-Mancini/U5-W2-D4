package davidemancini.U5_W2_D4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Autori {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autori (String nome, String congome, String email, LocalDate dataDiNascita){
        this.avatar="https://ui-avatars.com/api/?name="+nome+"+"+congome;
        this.nome=nome;
        this.cognome=congome;
        this.email=email;
        this.dataDiNascita=dataDiNascita;
    }
}
