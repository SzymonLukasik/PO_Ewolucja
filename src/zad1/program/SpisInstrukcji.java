package zad1.program;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class SpisInstrukcji {
    private final ArrayList<Instrukcja> instrukcje;

    public SpisInstrukcji(ArrayList<Instrukcja> instrukcje) {

        EnumSet<Instrukcja> zbiórUżytych = EnumSet.noneOf(Instrukcja.class);
        try {
            for (Instrukcja instrukcja : instrukcje) {
                if (zbiórUżytych.contains(instrukcja))
                    throw new IllegalArgumentException("Błędny argument. " +
                            "Spis instrukcji nie może zawierać powtarzających się instrukcji.");
                else
                    zbiórUżytych.add(instrukcja);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }

        this.instrukcje = instrukcje;
    }

    public Instrukcja dajLosową() {
        Random random = new Random();
        return instrukcje.get(random.nextInt(instrukcje.size()));
    }
}