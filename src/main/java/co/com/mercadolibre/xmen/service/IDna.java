package co.com.mercadolibre.xmen.service;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.model.Stat;

public interface IDna {

    DnaEntity getDna(String[] dna);
    void saveDna(String[] dna, boolean mutant);
    Stat getStatistics();

}
