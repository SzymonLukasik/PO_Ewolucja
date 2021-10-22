package zad1.pola;

import zad1.plansza.Plansza;
import zad1.parametry.pól.ParamPolaŻywieniowego;
import zad1.roby.Rob;

public class PoleŻywieniowe extends PoleAktywne{
    private final ParamPolaŻywieniowego parametry;
    private boolean czyMaPożywienie;
    private int ileDoRegeneracji;

    public PoleŻywieniowe(Plansza plansza, int współrzędnaX,
                          int współrzędnaY, ParamPolaŻywieniowego parametry) {
        super(plansza, współrzędnaX, współrzędnaY);

        this.parametry = parametry;
        this.czyMaPożywienie = true;
        this.ileDoRegeneracji = 0;
        plansza.dane().dodajPoleZŻywnością();
    }

    @Override
    public void wykonajTurę() {
        if(ileDoRegeneracji == 0 && !czyMaPożywienie) {
            czyMaPożywienie = true;
            ileDoRegeneracji = parametry.ileRośnieJedzenie();
            plansza.dane().dodajPoleZŻywnością();
        }
        ileDoRegeneracji--;
    }

    @Override
    public void wpuśćRoba(Rob rob) {
        super.wpuśćRoba(rob);
        if(czyMaPożywienie) {
            rob.zjedz();
            plansza.dane().usuńPoleZŻywnością();
            czyMaPożywienie = false;
            ileDoRegeneracji = parametry.ileRośnieJedzenie();
        }
    }

    @Override
    public boolean czyMaPożywienie() {
        return czyMaPożywienie;
    }
}
