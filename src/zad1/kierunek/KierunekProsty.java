package zad1.kierunek;

import java.util.Random;

public enum KierunekProsty {
    GÓRA("GÓRA"), PRAWO("PRAWO"),
    DÓŁ("DÓŁ"), LEWO("LEWO");

    private final String string;

    KierunekProsty(String string) {
        this.string = string;
    }

    public KierunekProsty przeciwny() {
        switch (this) {
            case GÓRA: {
                return DÓŁ;
            }
            case DÓŁ: {
                return GÓRA;
            }
            case PRAWO: {
                return LEWO;
            }
            case LEWO: {
                return PRAWO;
            }
            default: {
                return GÓRA;
            }
        }
    }

    public KierunekProsty obróćPrawo() {
        switch (this) {
            case GÓRA: {
                return PRAWO;
            }
            case PRAWO: {
                return DÓŁ;
            }
            case DÓŁ: {
                return LEWO;
            }
            case LEWO: {
                return GÓRA;
            }
            default: {
                return GÓRA;
            }
        }
    }

    public KierunekProsty obróćLewo() {
        switch (this) {
            case GÓRA: {
                return LEWO;
            }
            case LEWO: {
                return DÓŁ;
            }
            case DÓŁ: {
                return PRAWO;
            }
            case PRAWO: {
                return GÓRA;
            }
            default: {
                return GÓRA;
            }
        }
    }

    public static KierunekProsty dajLosowy() {
        Random random = new Random();
        return KierunekProsty.values()[random.nextInt(4)];
    }

    @Override
    public String toString() {
        return string;
    }

}
