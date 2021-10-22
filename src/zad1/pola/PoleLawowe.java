package zad1.pola;

import zad1.parametry.pól.ParamPolaLawowego;
import zad1.plansza.Plansza;
import zad1.roby.Rob;


public class PoleLawowe extends PoleAktywne {

    private final ParamPolaLawowego parametry;

    public PoleLawowe(Plansza plansza, int współrzędnaX, int współrzędnaY,
                      ParamPolaLawowego parametry) {
        super(plansza, współrzędnaX, współrzędnaY);
        this.parametry = parametry;
    }

    @Override
    public void wykonajTurę() {
        // Chcemy uniknąć modyfikowania kolekcji
        // podczas iteracji - stąd tworzenie tablicy.
        for(Rob rob: roby.toArray(new Rob[0]))
            rob.przyjmijObrażenia(parametry.ileZadajeLawa());
    }

    @Override
    public boolean czyMaPożywienie() {
        return false;
    }
}
