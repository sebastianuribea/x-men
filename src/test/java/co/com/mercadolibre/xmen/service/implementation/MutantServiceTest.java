package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.model.DnaEntity;
import co.com.mercadolibre.xmen.service.IDna;
import co.com.mercadolibre.xmen.service.IMutant;
import co.com.mercadolibre.xmen.service.ISearchSequence;
import co.com.mercadolibre.xmen.service.IValidatorDna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MutantServiceTest {

    IMutant mutantService;

    @Mock
    private IValidatorDna validatorDnaService;

    @Mock
    private ISearchSequence searchSequenceService;

    @Mock
    private IDna dnaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mutantService = new MutantService(validatorDnaService,searchSequenceService,dnaService);
    }

    @Test
    public void invalidDnaTest() {
        when(validatorDnaService.isNotNullAndMinOrderFour(any())).thenReturn(true);
        when(validatorDnaService.isSymmetrical(any())).thenReturn(false);
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA"});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void isNotMutantDbTest() {
        when(validatorDnaService.isNotNullAndMinOrderFour(any())).thenReturn(true);
        when(validatorDnaService.isSymmetrical(any())).thenReturn(true);
        when(validatorDnaService.containsOnlyAllowedValues(any())).thenReturn(true);
        when(searchSequenceService.getSequences(any())).thenReturn(0);
        when(dnaService.getDna(any())).thenReturn(DnaEntity.builder().id(1).dna("ATGCGACAGTGCTTATTTAGACGGGCGTCATCACTG").mutant(false).build());
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void isMutantDbTest() {
        when(validatorDnaService.isNotNullAndMinOrderFour(any())).thenReturn(true);
        when(validatorDnaService.isSymmetrical(any())).thenReturn(true);
        when(validatorDnaService.containsOnlyAllowedValues(any())).thenReturn(true);
        when(searchSequenceService.getSequences(any())).thenReturn(3);
        when(dnaService.getDna(any())).thenReturn(DnaEntity.builder().id(2).dna("ATGCGACAGTGCTTATGCAGAAGGCCCCTATCACTG").mutant(true).build());
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATGC","AGAAGG","CCCCTA","TCACTG"});
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void isNotMutantTest() {
        when(validatorDnaService.isNotNullAndMinOrderFour(any())).thenReturn(true);
        when(validatorDnaService.isSymmetrical(any())).thenReturn(true);
        when(validatorDnaService.containsOnlyAllowedValues(any())).thenReturn(true);
        when(searchSequenceService.getSequences(any())).thenReturn(0);
        when(dnaService.getDna(any())).thenReturn(null);
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void isMutantTest() {
        when(validatorDnaService.isNotNullAndMinOrderFour(any())).thenReturn(true);
        when(validatorDnaService.isSymmetrical(any())).thenReturn(true);
        when(validatorDnaService.containsOnlyAllowedValues(any())).thenReturn(true);
        when(searchSequenceService.getSequences(any())).thenReturn(3);
        when(dnaService.getDna(any())).thenReturn(null);
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATGC","AGAAGG","CCCCTA","TCACTG"});
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
