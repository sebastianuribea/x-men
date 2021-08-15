package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.service.IValidatorDna;
import org.springframework.stereotype.Service;

@Service
public class ValidatorDnaService implements IValidatorDna {

    @Override
    public boolean isNotNullAndMinOrderFour(String[] dna) {
        return dna != null && dna.length >= 4;
    }

    @Override
    public boolean isSymmetrical(String[] dna) {
        int n = dna.length;
        for (int i = 0; i < n; i++) {
            if(dna[i].length()!=n) return false;
        }
        return true;
    }

    @Override
    public boolean containsOnlyAllowedValues(String[] dna) {
        int n = dna.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!(dna[i].charAt(j) == 'A' || dna[i].charAt(j) == 'T' || dna[i].charAt(j) == 'C' || dna[i].charAt(j) == 'G'))
                    return false;
            }
        }
        return true;
    }
}
