package co.com.mercadolibre.xmen.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = MutantService.class)
public class MutantServiceTest {

    @InjectMocks
    MutantService mutantService;

    @Test
    public void invalidDnaTest() {
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA"});
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void isMutantTest() {
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void isNotMutantTest() {
        ResponseEntity response = mutantService.isMutant(new String[]{"ATGCGA","CAGTGC","TTATGC","AGAAGG","CCCCTA","TCACTG"});
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
