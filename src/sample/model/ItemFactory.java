package sample.model;

import java.io.File;

/**
 * Created by t on 4/30/17.
 */
//Factory pattern for two views

public class ItemFactory {

    public baseItem getItem(boolean tableRender,File file){
        if(tableRender){
            return new TableItem(file);
        }
        else{
            return new TileItem(file);
        }
    }
}
