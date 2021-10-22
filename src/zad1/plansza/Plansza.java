package zad1.plansza;

import zad1.parametry.ParamSymulacji;
import zad1.parametry.pól.ParamPól;
import zad1.parametry.roba.ParamRoba;
import zad1.pola.*;
import zad1.kierunek.KierunekProsty;
import zad1.kierunek.KierunekSkośny;
import zad1.roby.Rob;

import java.util.*;

public class Plansza {
    private final Pole[][] pola;
    private final int szerokość;
    private final int długość;
    private final NavigableSet<Rob> roby;
    private final ArrayList<Rob> powieloneRoby;
    private final ArrayList<PoleAktywne> polaAktywne;
    private final DanePlanszy dane;


    //Metody tworzące nową plansze

    public Plansza(char[][] znaki_pól,
                   ParamSymulacji parametry) {

        this.szerokość = znaki_pól[0].length;
        this.długość = znaki_pól.length;
        this.pola = new Pole[długość][szerokość];
        this.roby = new TreeSet<>(Rob::compareTo);
        this.powieloneRoby = new ArrayList<>();
        this.polaAktywne = new ArrayList<>();
        this.dane = new DanePlanszy(this);

        inicjujPlanszę(znaki_pól, parametry.paramPola());
        stwórzPołączenia();
        /*for(Pole[] t : pola) {
            for(Pole pole : t) {
                pole.wypiszSąsiadów();
            }
        }*/
        rozmiesćRoby(parametry.pocz_ile_robów(), parametry.paramRoba());
    }

    private void inicjujPlanszę(char[][] znaki_pól, ParamPól paramPola) {
        for(int i = 0; i < długość; i++) {
            for(int j = 0; j < szerokość; j++) {
                if(znaki_pól[i][j] == ' ')
                    this.pola[i][j] = new PolePuste(this, j, długość - 1 - i);
                else if(znaki_pól[i][j] == 'x')
                    this.pola[i][j] = new PoleŻywieniowe(this, j, długość - 1 - i,
                            paramPola.żywieniowego());
                else if(znaki_pól[i][j] == 'O')
                    this.pola[i][j] = new PoleLawowe(this, j, długość - 1 - i,
                            paramPola.lawowego());
            }
        }
    }

    // Łączy pola leżące na lewym i prawym brzegu wiersza nr i
    private void połączBrzegowe(Pole[] górni, int i) {
        Pole lewe = pola[i][0];
        Pole prawe = pola[i][szerokość - 1];

        // Łączymy pole leżące na lewym brzegu
        lewe.połącz(KierunekProsty.LEWO, prawe);
        lewe.połącz(KierunekSkośny.GÓRNE_LEWO, górni[szerokość - 1]);
        lewe.połącz(KierunekProsty.GÓRA, górni[0]);
        if(szerokość > 1)
            pola[i][0].połącz(KierunekSkośny.GÓRNE_PRAWO, górni[1]);
        else
            pola[i][0].połącz(KierunekSkośny.GÓRNE_PRAWO, górni[0]);

        //Jeśli szerokość > 1 łączymy pole leżące na prawym brzegu
        if(szerokość > 1) {
            prawe.połącz(KierunekProsty.LEWO, pola[i][szerokość - 2]);
            prawe.połącz(KierunekSkośny.GÓRNE_LEWO, górni[szerokość - 2]);
            prawe.połącz(KierunekProsty.GÓRA, górni[szerokość - 1]);
            prawe.połącz(KierunekSkośny.GÓRNE_PRAWO, górni[0]);
        }
    }

    private void stwórzPołączenia() {
        Pole[] górni = pola[długość - 1];

        for(int i = 0; i < długość; i++) {
            połączBrzegowe(górni, i);
            for(int j = 1; j < szerokość - 1; j++) {
                Pole pole = pola[i][j];
                pole.połącz(KierunekProsty.LEWO, pola[i][j - 1]);
                pole.połącz(KierunekSkośny.GÓRNE_LEWO, górni[j - 1]);
                pole.połącz(KierunekProsty.GÓRA, górni[j]);
                pole.połącz(KierunekSkośny.GÓRNE_PRAWO, górni[j + 1]);
            }
            górni = pola[i];
        }
    }

    public void rozmiesćRoby(int ileRobów, ParamRoba parametry) {
        Random czyNaTymPolu = new Random();

        while (ileRobów > 0)
            for (Pole[] wiersz : pola)
                for (Pole pole : wiersz)
                    while (czyNaTymPolu.nextBoolean() && ileRobów > 0)
                        roby.add(new Rob(this, ileRobów--,
                                         parametry.początkowe().poczProgram(),
                                         parametry.ostateczne(),
                                         pole, KierunekProsty.dajLosowy(),
                                         parametry.początkowe().poczEnergia()));
    }

    //Metody służące do wykonywania tury

    private void dodajPowielone() {
        for(Rob rob: powieloneRoby)
            dodajRoba(rob);

        powieloneRoby.clear();
    }

    public void wykonajTurę() {
        dane().nowaTura();

        for(PoleAktywne pole: polaAktywne)
            pole.wykonajTurę();

        // Chcemy uniknąć modyfikowania kolekcji
        // podczas iteracji - stąd tworzenie tablicy.
        for(Rob rob: roby.toArray(new Rob[0]))
            rob.wykonajTurę();

        dodajPowielone();
    }

    //Inne metody służące do komunikacji z planszą

    public DanePlanszy dane() {
        return dane;
    }

    public void dodajPoleAktywne(PoleAktywne pole) {
        polaAktywne.add(pole);
    }

    public void dodajDoPowielonych(Rob rob) {
        this.powieloneRoby.add(rob);
    }

    public void dodajRoba(Rob rob) {
        this.roby.add(rob);
    }

    public void usuńRoba(Rob rob) {
        this.roby.remove(rob);
    }

    //Metody wypisujące rozmieszczenie robów w odpowiednim formacie

    public void wypiszStanRobów() {
        if(dane().liczbaRobów() > 0) {
            System.out.println("Stan robów: ");
            for (Rob rob : roby)
                System.out.print(rob);
            System.out.println();

            System.out.println("Rozmieszczenie robów: ");
            wypiszRozmieszczenieRobów();
        }
        else
            System.out.println("Wszystkie roby umarły.");

        System.out.println();
    }

    //Metoda center wyśrodkowuje String s
    private String center(String s, int width) {
        return String.format("%-" + width  + "s",
               String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    private void wypiszRozmieszczenieRobów() {
        String[][] outPut = new String[długość + 1][szerokość + 1];

        //Ustawiamy szerokość kolumn
        int szerokośćWŚrodku = Integer.max(
                String.valueOf(dane().liczbaRobów()).length(),
                String.valueOf(szerokość).length()) + 3;
        int szerokośćLewyBrzeg = String.valueOf(długość).length() + 1;

        //Ustawiamy format wyjścia
        StringBuilder format = new StringBuilder();
        format.append("%").append(szerokośćLewyBrzeg).append("s");
        format.append(("%" + szerokośćWŚrodku + "s").repeat(szerokość));
        format.append("%n");
        String formatString = format.toString();

        //Inicjujemy macierz Stringów odpowiednimi wartościami
        for(int i = 1; i < długość + 1; i++) {
            for(int j = 1; j < szerokość + 1; j++) {
                int liczbaRobów = pola[i - 1][j - 1].liczbaRobów();
                if(liczbaRobów == 1)
                    outPut[i][j] = "R";
                else if(liczbaRobów > 1)
                    outPut[i][j] = "[" + liczbaRobów + "]";
                else if (liczbaRobów == 0)
                    outPut[i][j] = "";

                outPut[i][j] = center(outPut[i][j], szerokośćWŚrodku);
            }
        }
        for(int j = 1; j < szerokość + 1; j++)
            outPut[0][j] = center((Integer.valueOf(j - 1)).toString(), szerokośćWŚrodku);
        for(int i = 1; i < długość + 1; i++)
            outPut[i][0] = center(Integer.valueOf(długość - i).toString(), szerokośćLewyBrzeg);
        outPut[0][0] = "";

        //Wypisujemy rozmieszczenie robów
        for(int i = 0; i < długość + 1; i++)
            System.out.format(formatString, (Object[]) outPut[i]);

    }
}
