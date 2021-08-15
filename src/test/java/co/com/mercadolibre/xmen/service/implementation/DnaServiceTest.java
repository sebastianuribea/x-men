package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.repository.DnaRepository;
import co.com.mercadolibre.xmen.service.IDna;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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
        when(dnaRepository.findAll()).thenReturn(new ArrayList<DnaEntity>());
        JSONObject object = dnaService.getStatistics();
        assertEquals(0, object.get("count_mutant_dna"));
    }

    @Test
    public void getStatsOneMutantTest() {
        when(dnaRepository.findAll()).thenReturn(new ArrayList<DnaEntity>(){{add(DnaEntity.builder().id(1).dna("AATCCCCCTCGATTTT").mutant(true).build());}});
        JSONObject object = dnaService.getStatistics();
        assertEquals(1, object.get("count_mutant_dna"));
        assertEquals(0, object.get("count_human_dna"));
    }

    @Test
    public void getStatsOneMutantAndOneHumanTest() {
        when(dnaRepository.findAll()).thenReturn(new ArrayList<DnaEntity>(){{add(DnaEntity.builder().id(1).dna("AATCCCCCTCGATTTT").mutant(true).build()); add(DnaEntity.builder().id(2).dna("AGCTGTACTGCAGTAC").mutant(false).build());}});
        JSONObject object = dnaService.getStatistics();
        assertEquals(1, object.get("count_mutant_dna"));
        assertEquals(1, object.get("count_human_dna"));
    }


}
