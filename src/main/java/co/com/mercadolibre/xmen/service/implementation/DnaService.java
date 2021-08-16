package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.model.Stat;
import co.com.mercadolibre.xmen.repository.DnaRepository;
import co.com.mercadolibre.xmen.service.IDna;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

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
    public Stat getStatistics() {
        Stat stat = Stat.builder().countMutantDna(((BigInteger) dnaRepository.getCount(true).get("count_stats")).intValue())
                .countHumanDna(((BigInteger) dnaRepository.getCount(false).get("count_stats")).intValue()).build();
        if(stat.getCountHumanDna()> 0) stat.setRatio(stat.getCountMutantDna()/stat.getCountHumanDna());
        return stat;
    }
}
