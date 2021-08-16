package co.com.mercadolibre.xmen.model;

import lombok.*;

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
