package sample;

/**
 * Created by t on 4/30/17.
 */
public class Preferences {


    private static boolean LISTVIEW=true;
    private static boolean TILEVIEW=false;


    private static Preferences preferences;
    private boolean showMode;

    private Preferences(){
        showMode=TILEVIEW;
    }

    public static synchronized Preferences getInstance(){
        if (preferences==null){
            preferences=new Preferences();
        }
        return preferences;
    }

    public boolean activateView(){
        return showMode;
    }

}
