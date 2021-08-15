package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.repository.DnaRepository;
import co.com.mercadolibre.xmen.service.IDna;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DnaService implements IDna {

    private final DnaRepository dnaRepository;

    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @Override
    public DnaEntity getDna(String[] dna) {
        Optional<DnaEntity> dnadb = dnaRepository.findByDna(String.join("",dna));
        return dnadb.isPresent() ? dnadb.get() : null;
    }

    @Override
    public void saveDna(String[] dna, boolean mutant) {
        dnaRepository.save(DnaEntity.builder().dna(String.join("",dna)).mutant(mutant).build());
    }

    @Override
    public JSONObject getStatistics() {
        int mutant = 0;
        int human = 0;
        int ratio = 0;
        List<DnaEntity> dnas = dnaRepository.findAll();
        if(!dnas.isEmpty()){
            mutant = dnas.stream().filter(dna -> dna.isMutant()).collect(Collectors.toList()).size();
            human = dnas.size()-mutant;
            if(human > 0) ratio = mutant/human;
        }
        JSONObject json = new JSONObject();
        json.put("count_mutant_dna",mutant);
        json.put("count_human_dna",human);
        json.put("ratio", ratio);
        return json;
    }
}
