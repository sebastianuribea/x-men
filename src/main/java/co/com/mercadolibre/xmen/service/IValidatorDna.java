package co.com.mercadolibre.xmen.service;

public interface IValidatorDna {

    boolean isNotNullAndMinOrderFour(String[] dna);
    boolean isSymmetrical(String[] dna);
    boolean containsOnlyAllowedValues(String[] dna);

}
