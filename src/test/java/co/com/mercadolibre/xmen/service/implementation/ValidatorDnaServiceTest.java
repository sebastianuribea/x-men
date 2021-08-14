package co.com.mercadolibre.xmen.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ValidatorDnaService.class)
public class ValidatorDnaServiceTest {

    @InjectMocks
    ValidatorDnaService validatorDnaService;

    @Test
    public void dnaNullTest() {
        boolean response = validatorDnaService.isNotNullAndMinOrderFour(null);
        assertFalse(response);
    }

    @Test
    public void dnaEmptyTest() {
        boolean response = validatorDnaService.isNotNullAndMinOrderFour(new String[]{});
        assertFalse(response);
    }

    @Test
    public void dnaOrderlessThanFourTest() {
        boolean response = validatorDnaService.isNotNullAndMinOrderFour(new String[]{"ATC","GCA","TTA"});
        assertFalse(response);
    }

    @Test
    public void dnaOrderFourTest() {
        boolean response = validatorDnaService.isNotNullAndMinOrderFour(new String[]{"ATCT","GCAT","TTAT"});
        assertTrue(response);
    }

    @Test
    public void dnaIsNotSymmetricalTest() {
        boolean response = validatorDnaService.isSymmetrical(new String[]{"ATCG","GCAT","TTAA","GGA"});
        assertFalse(response);
    }

    @Test
    public void dnaIsSymmetricalTest() {
        boolean response = validatorDnaService.isSymmetrical(new String[]{"ATCG","GCAT","TTAA","GGAT"});
        assertTrue(response);
    }

    @Test
    public void dnaNotContainsOnlyAllowedValuesTest() {
        boolean response = validatorDnaService.containsOnlyAllowedValues(new String[]{"ATTG","GZAT","TPAA","GGAT"});
        assertFalse(response);
    }

    @Test
    public void dnaContainsOnlyAllowedValuesTest() {
        boolean response = validatorDnaService.containsOnlyAllowedValues(new String[]{"ATTGT","GGGAT","TAGAA","TGGAT","GTACG"});
        assertTrue(response);
    }


}
