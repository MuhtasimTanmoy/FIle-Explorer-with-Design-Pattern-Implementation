package sample.model;

import java.io.File;

/**
 * Created by t on 5/1/17.
 */
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
