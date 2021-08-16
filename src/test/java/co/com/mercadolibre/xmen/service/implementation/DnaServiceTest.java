package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.model.Stat;
import co.com.mercadolibre.xmen.repository.DnaRepository;
import co.com.mercadolibre.xmen.service.IDna;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.when;

public class DnaServiceTest {

    IDna dnaService;

    @Mock
    private DnaRepository dnaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dnaService = new DnaService(dnaRepository);
    }

    @Test
    public void dnaDbNullTest() {
        when(dnaRepository.findByDna(any())).thenReturn(Optional.empty());
        DnaEntity response = dnaService.getDna(new String[]{"ATCC","GCAC","TTAC","CCCT"});
        assertEquals(null,response);
    }

    @Test
    public void dnaDbNotNullTest() {
        when(dnaRepository.findByDna(any())).thenReturn(Optional.of(DnaEntity.builder().build()));
        DnaEntity response = dnaService.getDna(new String[]{"ATCC","GCAC","TTAC","CCCT"});
        assertNotEquals(null,response);
    }

    @Test
    public void saveDnaTest() {
        when(dnaRepository.save(any())).thenReturn(DnaEntity.builder().build());
        dnaService.saveDna(new String[]{"ATCC","GCAC","TTAC","CCCT"},false);
        calls(1);
    }

    @Test
    public void getStatsEmptyTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count_human",0);
        jsonObject.put("count_mutant",0);
        when(dnaRepository.getCount(any(Boolean.class))).thenReturn(jsonObject);
        Stat stats = dnaService.getStatistics();
        assertEquals(0, stats.getCountHumanDna());
    }

    @Test
    public void getStatsOneMutantTest() {
        JSONObject countMutant = new JSONObject();
        countMutant.put("count_mutant",1);
        JSONObject countHuman = new JSONObject();
        countHuman.put("count_human",0);
        when(dnaRepository.getCount(true)).thenReturn(countMutant);
        when(dnaRepository.getCount(false)).thenReturn(countHuman);
        Stat stats = dnaService.getStatistics();
        assertEquals(1, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
    }

    @Test
    public void getStatsOneMutantAndOneHumanTest() {
        JSONObject countMutant = new JSONObject();
        countMutant.put("count_mutant",1);
        JSONObject countHuman = new JSONObject();
        countHuman.put("count_human",1);
        when(dnaRepository.getCount(true)).thenReturn(countMutant);
        when(dnaRepository.getCount(false)).thenReturn(countHuman);
        Stat stats = dnaService.getStatistics();
        assertEquals(1, stats.getCountMutantDna());
        assertEquals(1, stats.getCountHumanDna());
    }


}
