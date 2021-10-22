package zad1;

import zad1.czytnik.CzytnikParametrów;
import zad1.czytnik.CzytnikPlanszy;
import zad1.parametry.ParamSymulacji;
import zad1.plansza.Plansza;

import java.io.File;
import java.io.IOException;

public class Symulacja {
    /*
     * Zadanie zostało rozszerzone o możliwość umieszczenia na planszy
     * pola lawowego - oznaczanego symbolem "O".
     * W parametrach należy podać liczbę punktów energii zabieranych przez
     * pole lawowe na początku każdej tury wszystkim robom, które na nim stoją.
     *
     * Opis implementacji:
     * Podane parametry przetrzymujemy jako obiekty osobnych klas.
     * Kierunki oraz Instrukcje reprezentujemy przy pomocy klasy Enum.
     *
     * Głównymi obiektami biorącymi udział w symulacji są pola, roby oraz programy.
     * Za przygotowanie obiektów do wykonania symulacji, przebieg każdej tury
     * oraz wypisanie informacji o turze i rozmieszczeniu robów na planszy,
     * odpowiedzialna jest klasa Plansza.
     * Klasa Symulacja, przy użyciu klasy Czytnik wczytuje parametry oraz znaki pól,
     * których używa do zainicjowania planszy i następnie wykonuje symulacje
     * zlecając planszy wypisywanie informacji co odpowiednią ilość tur.
     */

    private final char[][] znakiPól;
    private final ParamSymulacji parametry;
    private Plansza plansza;

    public Symulacja(char[][] znakiPól, ParamSymulacji parametry) {
        this.znakiPól = znakiPól;
        this.parametry = parametry;
    }

    private void inicjujSymulację() {
        plansza = new Plansza(znakiPól, parametry);
    }


    public void wykonaj() {
        inicjujSymulację();
        plansza.wypiszStanRobów();

        for(int i = 1; i <= parametry.ile_tur(); i++) {
            plansza.wykonajTurę();
            System.out.println(plansza.dane());
            if(i % parametry.co_ile_wypisz() == 0 || i == parametry.ile_tur())
                plansza.wypiszStanRobów();
        }
    }

    public static void main(String[] args) {
        if(args.length == 2) {
            CzytnikPlanszy czytnikPlanszy = new CzytnikPlanszy(new File(args[0]));
            char[][] znakiPól = czytnikPlanszy.wczytajPlanszę();

            CzytnikParametrów czytnikParametrów = new CzytnikParametrów(new File(args[1]));
            ParamSymulacji parametry = czytnikParametrów.wczytajParametry();

            Symulacja symulacja = new Symulacja(znakiPól, parametry);
            symulacja.wykonaj();
        }
        else {
            try {
                throw new IOException("Parametrami programu powinny " +
                        "być dwa pliki tekstowe.");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}