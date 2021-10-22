package zad1.program;

public enum Instrukcja {
    LEWO("l"), PRAWO("p"), IDŹ("i"),
    WĄCHAJ("w"), JEDZ("j");

    private final String string;

    Instrukcja(String string) {
        this.string = string;
    }

    public static Instrukcja dajInstrukcję(char c) {
        switch (c) {
            case 'l': {
                return Instrukcja.LEWO;
            }
            case 'p': {
                return Instrukcja.PRAWO;
            }
            case 'i': {
                return Instrukcja.IDŹ;
            }
            case 'w': {
                return Instrukcja.WĄCHAJ;
            }
            case 'j': {
                return Instrukcja.JEDZ;
            }
            default: throw new IllegalArgumentException( "Błędny argument: " +
                   "\"" + c  +"\"" + " nie reprezentuje żadnej instrukcji.");
        }
    }

    @Override
    public String toString() {
        return string;
    }
}
