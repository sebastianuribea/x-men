package co.com.mercadolibre.xmen.service;

public interface ISearchSequence {

    int getHorizontal(String[] dna);
    int getVertical(String[] dna);
    int getDiagonal(String[] dna);
    int getInverseDiagonal(String[] dna);

}
