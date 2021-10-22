package zad1.parametry.pól;

public class ParamPól {

    private final ParamPolaŻywieniowego żywieniowego;
    private final ParamPolaLawowego lawowego;

    public ParamPól() {
        this.lawowego = new ParamPolaLawowego();
        this.żywieniowego = new ParamPolaŻywieniowego();
    }

    public ParamPolaŻywieniowego żywieniowego() {
        return żywieniowego;
    }

    public ParamPolaLawowego lawowego() {
        return  lawowego;
    }
}
