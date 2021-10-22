package zad1.parametry.roba;

public class ParamRoba {

    private final ParamRobaPoczątkowe początkowe;
    private final ParamRobaOstateczne ostateczne;

    public ParamRoba() {
        this.początkowe = new ParamRobaPoczątkowe();
        this.ostateczne = new ParamRobaOstateczne();
    }

    //Gettery

    public ParamRobaPoczątkowe początkowe() {
        return początkowe;
    }

    public ParamRobaOstateczne ostateczne() {
        return ostateczne;
    }

}
