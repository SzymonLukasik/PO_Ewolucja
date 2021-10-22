package zad1.czytnik;

import zad1.parametry.*;
import zad1.program.Instrukcja;
import zad1.program.SpisInstrukcji;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.stream.Collectors;

public class CzytnikParametrów extends Czytnik {

    private ParamSymulacji parametry;
    private EnumSet<Parametr> wczytaneParametry;


    private enum Parametr {
        POCZ_ILE_ROBÓW,
        ILE_TUR,
        CO_ILE_WYPISZ,
        POCZ_PROGR,
        POCZ_ENERGIA,
        KOSZT_TURY,
        PR_POWIELANIA,
        UŁAMEK_ENERGII_RODZICA,
        ILE_DAJE_JEDZENIE,
        LIMIT_POWIELANIA,
        ILE_ROŚNIE_JEDZENIE,
        PR_USUNIĘCIA_INSTR,
        PR_DODANIA_INSTR,
        PR_ZMIANY_INSTR,
        SPIS_INSTR,
        ILE_ZADAJE_LAWA
    }

    @Override
    protected void inicjujPolaPomocnicze() {
        parametry = new ParamSymulacji();
        wczytaneParametry = EnumSet.noneOf(Parametr.class);
    }

    public CzytnikParametrów(File plik) {
        super(plik);
    }


    public ParamSymulacji wczytajParametry() {
        try {
            wczytajWiersze();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if(wczytaneParametry.size() != Parametr.values().length) {
            try {
                throw new IOException("Błędny format wejścia. " +
                    "Za mała ilość podanych parametrów.");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        parametry.paramRoba().początkowe().poczProgram()
                 .ustawParametry(parametry.paramProgramu());

        return parametry;
    }

    private void dodajDoWczytanych(Parametr parametr) throws IOException {
        if (wczytaneParametry.contains(parametr))
            throw new IOException("Błędny format wejścia. " +
                    "Każdy parametr musi wystąpić dokładnie raz.");
        wczytaneParametry.add(parametr);
    }

    private ArrayList<Instrukcja> parsujCiągInstrukcji(String ciągInstrukcji) {
        ArrayList<Instrukcja> wynik = null;
        try {
             wynik = new ArrayList<>(ciągInstrukcji.chars()
                    .mapToObj(c -> Instrukcja.dajInstrukcję((char) c))
                    .collect(Collectors.toList()));
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return wynik;
    }

    @Override
    protected void wczytajWiersz() throws IOException {

        String wiersz = skaner.nextLine();
        String[] paramTab = wiersz.split(" ");

        if (paramTab.length == 2) {
            switch (paramTab[0]) {
                case "pocz_ile_robów": {
                    dodajDoWczytanych(Parametr.POCZ_ILE_ROBÓW);
                    parametry.ustawPocz_ile_robów(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "ile_tur": {
                    dodajDoWczytanych(Parametr.ILE_TUR);
                    parametry.ustawIle_tur(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "co_ile_wypisz": {
                    dodajDoWczytanych(Parametr.CO_ILE_WYPISZ);
                    parametry.ustawCo_ile_wypisz(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "pocz_progr": {
                    dodajDoWczytanych(Parametr.POCZ_PROGR);
                    ArrayList<Instrukcja> kod = parsujCiągInstrukcji(paramTab[1]);
                    parametry.paramRoba().początkowe().poczProgram().ustawKod(kod);
                    break;
                }
                case "pocz_energia": {
                    dodajDoWczytanych(Parametr.POCZ_ENERGIA);
                    parametry.paramRoba().początkowe()
                            .ustawPoczEnergia(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "koszt_tury": {
                    dodajDoWczytanych(Parametr.KOSZT_TURY);
                    parametry.paramRoba().ostateczne()
                            .ustawKosztTury(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "pr_powielania": {
                    dodajDoWczytanych(Parametr.PR_POWIELANIA);
                    parametry.paramRoba().ostateczne()
                            .ustawPrPowielania(Float.parseFloat(paramTab[1]));
                    break;
                }
                case "ułamek_energii_rodzica": {
                    dodajDoWczytanych(Parametr.UŁAMEK_ENERGII_RODZICA);
                    parametry.paramRoba().ostateczne()
                            .ustawUłamekEnergiiRodzica(Float.parseFloat(paramTab[1]));
                    break;
                }
                case "ile_daje_jedzenie": {
                    dodajDoWczytanych(Parametr.ILE_DAJE_JEDZENIE);
                    parametry.paramRoba().ostateczne()
                            .ustawIleDajeJedzenie(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "limit_powielania": {
                    dodajDoWczytanych(Parametr.LIMIT_POWIELANIA);
                    parametry.paramRoba().ostateczne()
                            .ustawLimitPowielania(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "ile_rośnie_jedzenie": {
                    dodajDoWczytanych(Parametr.ILE_ROŚNIE_JEDZENIE);
                    parametry.paramPola().żywieniowego()
                            .ustawIleRośnieJedzenie(Integer.parseInt(paramTab[1]));
                    break;
                }
                case "pr_dodania_instr": {
                    dodajDoWczytanych(Parametr.PR_DODANIA_INSTR);
                    parametry.paramProgramu()
                            .ustawPrDodaniaInstr(Float.parseFloat(paramTab[1]));
                    break;
                }
                case "pr_zmiany_instr": {
                    dodajDoWczytanych(Parametr.PR_ZMIANY_INSTR);
                    parametry.paramProgramu()
                            .ustawPrZmianyInstr(Float.parseFloat(paramTab[1]));
                    break;
                }
                case "pr_usunięcia_instr": {
                    dodajDoWczytanych(Parametr.PR_USUNIĘCIA_INSTR);
                    parametry.paramProgramu()
                            .ustawPrUsunięciaInstr(Float.parseFloat(paramTab[1]));
                    break;
                }
                case "spis_instr": {
                    dodajDoWczytanych(Parametr.SPIS_INSTR);
                    ArrayList<Instrukcja> instrukcje = parsujCiągInstrukcji(paramTab[1]);
                    parametry.paramProgramu()
                            .ustawSpisInstrukcji(new SpisInstrukcji(instrukcje));
                    break;
                }
                case "ile_zadaje_lawa": {
                    dodajDoWczytanych(Parametr.ILE_ZADAJE_LAWA);
                    parametry.paramPola().lawowego()
                            .ustawIleZadajeLawa(Integer.parseInt(paramTab[1]));
                    break;
                }
                default: {
                    throw new IOException("Błedny format wejścia. " +
                            "Nieznane parametry.");
                }
            }
        } else {
            throw new IOException("Błedny format wejścia. " +
                    "Wiersz powinien być postaci: " +
                    "[nazwa_parametru] [wartość_parametru]");
        }
    }
}

