package zad1.pola;

import zad1.plansza.Plansza;
import zad1.kierunek.KierunekProsty;
import zad1.kierunek.KierunekSkośny;
import zad1.roby.Rob;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class Pole implements Comparable<Pole> {

    protected Plansza plansza;
    private final int współrzędnaX;
    private final int współrzędnaY;
    private final EnumMap<KierunekProsty, Pole> sąsiedziPr;
    private final EnumMap<KierunekSkośny, Pole> sąsiedziSk;

    protected Set<Rob> roby;

    public Pole(Plansza plansza, int współrzędnaX, int współrzędnaY) {
        this.plansza = plansza;
        this.współrzędnaX = współrzędnaX;
        this.współrzędnaY = współrzędnaY;
        this.sąsiedziPr = new EnumMap<>(KierunekProsty.class);
        this.sąsiedziSk = new EnumMap<>(KierunekSkośny.class);
        this.roby = new TreeSet<>(Rob::compareTo);
    }

    @Override
    public int compareTo(Pole pole) {
        if(this.współrzędnaX == pole.współrzędnaX)
            return Integer.compare(this.współrzędnaY, pole.współrzędnaY);
        return Integer.compare(this.współrzędnaX, pole.współrzędnaX);
    }

    @Override
    public String toString() {
        return "(" + współrzędnaX + ", " + współrzędnaY + ")";
    }

    public void przydzielSąsiada(KierunekProsty kierunek, Pole sąsiad) {
        sąsiedziPr.put(kierunek, sąsiad);
    }

    public void przydzielSąsiada(KierunekSkośny kierunek, Pole sąsiad) {
        sąsiedziSk.put(kierunek, sąsiad);
    }

    public void połącz(KierunekProsty kierunek, Pole sąsiad) {
        this.przydzielSąsiada(kierunek, sąsiad);
        sąsiad.przydzielSąsiada(kierunek.przeciwny(), this);
    }

    public void połącz(KierunekSkośny kierunek, Pole sąsiad) {
        this.przydzielSąsiada(kierunek, sąsiad);
        sąsiad.przydzielSąsiada(kierunek.przeciwny(), this);
    }

    //Metoda używana bezpośrednio jedynie podczas inicjacji planszy.
    public void przydzielRoba(Rob rob) {
        this.roby.add(rob);
    }

    public void wpuśćRoba(Rob rob) {
        przydzielRoba(rob);
    }

    public void usuńRoba(Rob rob) {
        this.roby.remove(rob);
    }

    public Pole dajSąsiada(KierunekProsty kierunek) {
        return sąsiedziPr.get(kierunek);
    }

    public EnumMap<KierunekProsty, Pole> dajSąsiadówPr() {
        return sąsiedziPr;
    }

    public EnumMap<KierunekSkośny, Pole> dajSąsiadówSk() {
        return sąsiedziSk;
    }

    public abstract boolean czyMaPożywienie();

    public int liczbaRobów() {
        return roby.size();
    }

    /*public void wypiszSąsiadów() {
        System.out.println("Pole " + this + " ma sąsiadów:\n");
        for(Map.Entry<KierunekProsty, Pole> e: sąsiedziPr.entrySet()) {
            System.out.println("\t" + e.getKey() + " " + e.getValue());
        }
        for(Map.Entry<KierunekSkośny, Pole> e: sąsiedziSk.entrySet()) {
            System.out.println("\t" + e.getKey() + " " + e.getValue());
        }
    }*/
}
