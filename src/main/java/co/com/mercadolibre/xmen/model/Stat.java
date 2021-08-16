package co.com.mercadolibre.xmen.model;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stat {
    private int countMutantDna;
    private int countHumanDna;
    private int ratio;
}
