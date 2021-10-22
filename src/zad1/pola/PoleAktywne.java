package zad1.pola;

import zad1.plansza.Plansza;

public abstract class PoleAktywne extends  Pole{

    protected PoleAktywne(Plansza plansza, int współrzędnaX, int współrzędnaY) {
        super(plansza, współrzędnaX, współrzędnaY);
        plansza.dodajPoleAktywne(this);
    }

    public abstract void wykonajTurę();
}
