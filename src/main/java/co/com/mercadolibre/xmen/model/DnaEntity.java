package co.com.mercadolibre.xmen.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dna")
public class DnaEntity implements Serializable {

    @Id
    @Column(name = "dna_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dna")
    private String dna;

    @Column(name = "mutant")
    private boolean mutant;

}
