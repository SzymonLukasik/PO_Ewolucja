package zad1.plansza.statystyka;

import zad1.plansza.Plansza;
import zad1.roby.Rob;

import java.util.TreeMap;

public abstract class Statystyka {

    protected final Plansza plansza;
    private final TreeMap<Integer, Integer> mapa;
    private Integer minWartość;
    private Integer sumaWartości;
    private Integer maksWartość;

    protected abstract int dajWartość(Object o);

    private void inicjujWartości() {
        this.minWartość = null;
        this.sumaWartości = null;
        this.maksWartość = null;
    }

    protected Statystyka(Plansza plansza) {
        this.plansza = plansza;
        this.mapa = new TreeMap<>(Integer::compareTo);
        inicjujWartości();
    }

    public void dodajWartość(Rob rob) {
        int wartość = dajWartość(rob);

        if(mapa.get(wartość) == null) {
            mapa.put(wartość, 1);
        }
        else {
            mapa.put(wartość, mapa.get(wartość) + 1);
        }

        if(minWartość == null) {
            minWartość = wartość;
            sumaWartości = wartość;
            maksWartość = wartość;
        }
        else {
            minWartość = Integer.min(minWartość, wartość);
            maksWartość = Integer.max(maksWartość, wartość);
            sumaWartości += wartość;
        }
    }

    public  void usuńWartość(Rob rob) {
        int wartość = dajWartość(rob);

        mapa.put(wartość, mapa.get(wartość) - 1);

        if(mapa.get(wartość) == 0) {
            if(mapa.size() == 1) {
                mapa.remove(wartość);
                inicjujWartości();
            }
            else {
                if(wartość == minWartość)
                    minWartość = mapa.higherKey(wartość);
                if(wartość == maksWartość)
                    maksWartość = mapa.lowerKey(wartość);
                sumaWartości -= wartość;
                mapa.remove(wartość);
            }
        }
        else {
            sumaWartości -= wartość;
        }
    }

    public int minWartość() {
        return minWartość;
    }

    public float śrWartość() {
        return (float) (sumaWartości / plansza.dane().liczbaRobów());
    }

    public int maksWartość() {
        return maksWartość;
    }

    @Override
    public String toString() {
        return "min - " + this.minWartość()+
                ", śr - " + this.śrWartość() +
                ", maks - " + this.maksWartość() +
                "\n";
    }
}
