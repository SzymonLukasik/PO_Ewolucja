package zad1.pola;

import zad1.plansza.Plansza;

public class PolePuste extends Pole{

    public PolePuste(Plansza plansza, int współrzędnaX, int współrzędnaY) {
        super(plansza, współrzędnaX, współrzędnaY);
    }

    @Override
    public boolean czyMaPożywienie() {
        return false;
    }

}
