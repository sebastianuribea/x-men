package co.com.mercadolibre.xmen.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = SearchSequenceService.class)
public class SearchSequenceServiceTest {

    @InjectMocks
    SearchSequenceService searchSequenceService;

    @Test
    public void zeroSequencesTest() {
        int response = searchSequenceService.getSequences(new String[]{"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"});
        assertEquals(0, response);
    }

    @Test
    public void threeSequencesTest() {
        int response = searchSequenceService.getSequences(new String[]{"ATGCGA","CAGTGC","TTATGC","AGAAGG","CCCCTA","TCACTG"});
        assertEquals(3, response);
    }

    @Test
    public void zeroSequencesHorizontalTest() {
        int response = searchSequenceService.getHorizontal(new String[]{"ATCC","GATA","CGAG","TCGC"});
        assertEquals(0, response);
    }

    @Test
    public void twoSequencesHorizontalTest() {
        int response = searchSequenceService.getHorizontal(new String[]{"ATCCATG","TTTTAGC","TTCCGAG","TGCCCCA","GGGATCG","TAATCTC","TGCTAGC"});
        assertEquals(2, response);
    }

    @Test
    public void threeSequencesHorizontalTest() {
        int response = searchSequenceService.getHorizontal(new String[]{"GATCCATG","GATTTTTC","AACTTCCG","ATGCCCCA","TCGTAAAA","GTAATCTC","GTGCTAGC","ATACTAGC"});
        assertEquals(3, response);
    }

    @Test
    public void zeroSequencesVerticalTest() {
        int response = searchSequenceService.getVertical(new String[]{"ATCC","GATA","CGAG","TCGC"});
        assertEquals(0, response);
    }

    @Test
    public void twoSequencesVerticalTest() {
        int response = searchSequenceService.getVertical(new String[]{"ATCCATG","TGCTAGC","TTCCGAG","TGCATCA","TGGATCG","TAATCTC","TGCTAGC"});
        assertEquals(2, response);
    }

    @Test
    public void threeSequencesVerticalTest() {
        int response = searchSequenceService.getVertical(new String[]{"ATCCATGG","AGCTAGCC","GTACGAGG","CGCATCTA","TGCATCTC","AACTCTTC","GGCTAGTC","ATCCATGC"});
        assertEquals(3, response);
    }

    @Test
    public void zeroSequencesDiagonalTest() {
        int response = searchSequenceService.getDiagonal(new String[]{"ATCC","GATA","CGAG","TCGC"});
        assertEquals(0, response);
    }

    @Test
    public void twoSequencesDiagonalTest() {
        int response = searchSequenceService.getDiagonal(new String[]{"ATCAATG","TGTTAGC","TTTCGAG","AGCTTCA","TGGATCG","AAATCTC","TGCTAGT"});
        assertEquals(2, response);
    }

    @Test
    public void threeSequencesDiagonalTest() {
        int response = searchSequenceService.getDiagonal(new String[]{"GCCAATGG","AGCTAGCC","GTGCGAGG","CGCATCAA","TGTATCTA","AACTCTTC","GGCTTGTC","ATCCATGT"});
        assertEquals(3, response);
    }

    @Test
    public void zeroSequencesInverseDiagonalTest() {
        int response = searchSequenceService.getInverseDiagonal(new String[]{"ATCC","GATA","CGAG","TCGC"});
        assertEquals(0, response);
    }

    @Test
    public void twoSequencesInverseDiagonalTest() {
        int response = searchSequenceService.getInverseDiagonal(new String[]{"ATCAATG","TGTTAGC","TTTGGAC","AGGTTCA","TGGACCG","GAACCAC","TGCTAGC"});
        assertEquals(2, response);
    }

    @Test
    public void threeSequencesInverseDiagonalTest() {
        int response = searchSequenceService.getInverseDiagonal(new String[]{"GCCAGTGG","AGCGAGTC","GTGCGTGG","CGCATCCA","TGTGACTA","AAGACTTC","GGCTTGTC","GTCTATGT"});
        assertEquals(3, response);
    }
}
