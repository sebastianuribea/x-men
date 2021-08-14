package co.com.mercadolibre.xmen.service.implementation;

import co.com.mercadolibre.xmen.service.ISearchSequence;

public class SearchSequenceService implements ISearchSequence {

    @Override
    public int getSequences(String[] dna) {
        return getHorizontal(dna) + getVertical(dna) + getDiagonal(dna) + getInverseDiagonal(dna);
    }

    @Override
    public int getHorizontal(String[] dna) {
        int n = dna.length;
        int sequences = 0;
        int countRow = 1;
        for (int i = 0; i < n; i++) {
            char character = dna[i].charAt(0);
            for (int j = 0; j < n - 1; j++) {
                char nextCharacter = dna[i].charAt(j + 1);
                if (character == nextCharacter) {
                    countRow++;
                } else {
                    if (countRow >= 4) sequences++;
                    countRow = 1;
                    character = nextCharacter;
                }
            }
            if (countRow >= 4) sequences++;
            countRow = 1;
        }
        return sequences;
    }

    @Override
    public int getVertical(String[] dna) {
        int n = dna.length;
        int sequences = 0;
        int countRow = 1;
        for (int i = 0; i < n; i++) {
            char character = dna[0].charAt(i);
            for (int j = 0; j < n - 1; j++) {
                char nextCharacter = dna[j + 1].charAt(i);
                if (character == nextCharacter) {
                    countRow++;
                } else {
                    if (countRow >= 4) sequences++;
                    countRow = 1;
                    character = nextCharacter;
                }
            }
            if (countRow >= 4) sequences++;
            countRow = 1;
        }
        return sequences;
    }

    @Override
    public int getDiagonal(String[] dna) {
        int n = dna.length;
        int i = n - 4;
        int j = 0;
        int k = 4;
        int countRow = 1;
        int sequences = 0;
        while (i >= 0 && j <= n - 4) {
            int ii = i;
            int jj = j;
            char character = dna[ii].charAt(jj);
            for (int l = 0; l < k-1; l++) {
                char nextCharacter = dna[ii+1].charAt(jj+1);
                if (character == nextCharacter) {
                    countRow++;
                } else {
                    if (countRow >= 4) sequences++;
                    countRow = 1;
                    character = nextCharacter;
                }
                ii++;
                jj++;
            }
            if (countRow >= 4) sequences++;
            countRow = 1;
            if (i == 0) {
                j++;
                k--;
            } else {
                i--;
                k++;
            }
        }
        return sequences;
    }

    @Override
    public int getInverseDiagonal(String[] dna) {
        int n = dna.length;
        int i = n - 4;
        int j = n-1;
        int k = 4;
        int countRow = 1;
        int sequences = 0;
        while (i >= 0 && j >= n - 4) {
            int ii = i;
            int jj = j;
            char character = dna[ii].charAt(jj);
            for (int l = 0; l < k-1; l++) {
                char nextCharacter = dna[ii+1].charAt(jj-1);
                if (character == nextCharacter) {
                    countRow++;
                } else {
                    if (countRow >= 4) sequences++;
                    countRow = 1;
                    character = nextCharacter;
                }
                ii++;
                jj--;
            }
            if (countRow >= 4) sequences++;
            countRow = 1;
            if (i == 0) {
                j--;
                k--;
            } else {
                i--;
                k++;
            }
        }
        return sequences;
    }
}
