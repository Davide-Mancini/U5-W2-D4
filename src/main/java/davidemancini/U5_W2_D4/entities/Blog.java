package davidemancini.U5_W2_D4.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Blog {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
private UUID id;
private String categoria;
private String titolo;
private String cover;
private String contenuto;
private int tempoDiLettura;
@ManyToOne
@JoinColumn(name = "author_id")
private Autori author_id;

public Blog (String categoria, String titolo, String contenuto, int tempoDiLettura,Autori author_id){
    this.cover= "https://picsum.photos/200/300";
    this.categoria=categoria;
    this.titolo=titolo;
    this.contenuto=contenuto;
    this.tempoDiLettura = tempoDiLettura;
    this.author_id=author_id;
}
}
