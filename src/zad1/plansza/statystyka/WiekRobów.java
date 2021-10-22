package zad1.plansza.statystyka;

import zad1.plansza.Plansza;
import zad1.roby.Rob;

public class WiekRobów extends Statystyka {

    public WiekRobów(Plansza plansza) {
        super(plansza);
    }

    @Override
    protected int dajWartość(Object rob) {
        return ((Rob) rob).turaUrodzin();
    }

    @Override
    public int minWartość() {
        return plansza.dane().nrTury() - super.maksWartość();
    }

    @Override
    public int maksWartość() {
        return plansza.dane().nrTury() - super.minWartość();
    }

    @Override
    public float śrWartość() {
        return plansza.dane().nrTury() - super.śrWartość();
    }

    @Override
    public String toString() {
        return  "Wiek robów: " + super.toString();
    }

}
