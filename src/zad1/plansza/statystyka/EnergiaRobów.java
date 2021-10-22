package zad1.plansza.statystyka;

import zad1.plansza.Plansza;
import zad1.roby.Rob;

public class EnergiaRobów extends Statystyka {

    public EnergiaRobów(Plansza plansza) {
        super(plansza);
    }

    @Override
    protected int dajWartość(Object rob) {
        return ((Rob) rob).staraEnergia();
    }

    @Override
    public String toString() {
        return  "Energia robów: " + super.toString();
    }
}
