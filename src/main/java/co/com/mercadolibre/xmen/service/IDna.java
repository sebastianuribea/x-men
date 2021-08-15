package co.com.mercadolibre.xmen.service;

import co.com.mercadolibre.xmen.model.Dna;
import co.com.mercadolibre.xmen.model.DnaEntity;
import org.json.simple.JSONObject;

public interface IDna {

    DnaEntity getDna(String[] dna);
    void saveDna(String[] dna, boolean mutant);
    JSONObject getStatistics();

}
