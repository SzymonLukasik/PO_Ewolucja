package zad1.kierunek;

public enum KierunekSkośny {
    GÓRNE_PRAWO, DOLNE_PRAWO, DOLNE_LEWO, GÓRNE_LEWO;

    public KierunekSkośny przeciwny() {
        switch (this) {
            case GÓRNE_PRAWO: {
                return DOLNE_LEWO;
            }
            case DOLNE_PRAWO: {
                return GÓRNE_LEWO;
            }
            case DOLNE_LEWO: {
                return GÓRNE_PRAWO;
            }
            case GÓRNE_LEWO: {
                return DOLNE_PRAWO;
            }
            default: {
                return GÓRNE_PRAWO;
            }
        }
    }
}
