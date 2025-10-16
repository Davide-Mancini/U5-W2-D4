package davidemancini.U5_W2_D4.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class BlogsPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID author_id;
}
